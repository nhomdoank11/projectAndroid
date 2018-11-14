package com.example.jcj.learningskid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class UserSearchDictionary extends AppCompatActivity {

    TextView tvTransU, tvTrans2U;
    AutoCompleteTextView autoCompleteTextViewU;
    private List<Dictionary> dictionaryListU = new ArrayList<>();
    private static final String ID = "id";
    private static final String VIETNAM = "vietNam";
    private static final String ENGLISH = "english";
    private static final String EXAMPLE = "example";
    private static final String FOLDER_TASK = "DicProduct";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_dictionary);
        tvTrans2U = findViewById(R.id.tvUTrans2);
        autoCompleteTextViewU = findViewById(R.id.autoComplateU);
        getAutoCompleteU();
    }

    public void getAutoCompleteU() {
        MainActivity.learningskid.collection(FOLDER_TASK).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        final ArrayList<String> dictionaryEnglishListU = new ArrayList<>();
                        for (DocumentSnapshot doc : documentSnapshots) {
                            dictionaryEnglishListU.add((String) doc.get(ENGLISH));
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UserSearchDictionary.this,
                                R.layout.support_simple_spinner_dropdown_item,dictionaryEnglishListU);
                        autoCompleteTextViewU.setAdapter(arrayAdapter);
                        autoCompleteTextViewU.setThreshold(1);
                        autoCompleteTextViewU.setDropDownHeight(400);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("load e ", e.toString());
                        Toast.makeText(UserSearchDictionary.this, "Failed Load!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void getDictionaryByEn(View view) {
        tvTrans2U.setText("");
        final String txtAutoCompleteU = autoCompleteTextViewU.getText().toString();
        dictionaryListU = new ArrayList<>();
        MainActivity.learningskid.collection(FOLDER_TASK).whereEqualTo(ENGLISH,txtAutoCompleteU).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        for (DocumentSnapshot doc : documentSnapshots) {
                            Dictionary dictionary = new Dictionary(doc.getString(ID),doc.getString(VIETNAM), doc.getString(ENGLISH),
                                    doc.getString(EXAMPLE));
                            dictionaryListU.add(dictionary);
                            if(dictionaryListU.size() > 0){
                                tvTrans2U.setText("Translation of " + txtAutoCompleteU + ":" + "\n" + "\n" +
                                        "Means: " + doc.getString(VIETNAM) + "\n" + "\n" + "Other Meanings: " + doc.getString(EXAMPLE));
                            }
                        }
                        if(dictionaryListU.size() == 0) {
                            tvTrans2U.setText("Your search is not available!");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("load e ", e.toString());
                        Toast.makeText(UserSearchDictionary.this, "Failed Load!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void goAddNewWord (View v){
        Intent intent = new Intent(getApplicationContext(),UserDictionary.class);
        startActivity(intent);
        finish();
    }
    public void backUEContent(View view){
        Intent intent = new Intent(getApplicationContext(),UserEnglishContent.class);
        startActivity(intent);
        finish();
    }
}
