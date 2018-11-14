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

import java.util.ArrayList;
import java.util.List;

public class AdminSearchDictionary extends AppCompatActivity {
    private ListView listView;
    TextView tvTrans, tvTrans2;
    String uName;
    AutoCompleteTextView autoCompleteTextView;
    private List<Dictionary> dictionaryList = new ArrayList<>();
    private static final String ID = "id";
    private static final String VIETNAM = "vietNam";
    private static final String ENGLISH = "english";
    private static final String EXAMPLE = "example";
    private static final String FOLDER_TASK = "DicProduct";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin__search__dictionary);
        tvTrans = findViewById(R.id.tvTrans);
        tvTrans2 = findViewById(R.id.tvTrans2);
        autoCompleteTextView = findViewById(R.id.autoComplate);
        Intent intent = getIntent();
        uName = intent.getStringExtra("uName");

        getAutoComplete();
    }

    public void getAutoComplete() {
        MainActivity.learningskid.collection("DicProduct").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        final ArrayList<String> dictionaryEnglishList = new ArrayList<>();
                        for (DocumentSnapshot doc : documentSnapshots) {
                            dictionaryEnglishList.add((String) doc.get(ENGLISH));
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AdminSearchDictionary.this,
                                R.layout.support_simple_spinner_dropdown_item,dictionaryEnglishList);
                        autoCompleteTextView.setAdapter(arrayAdapter);
                        autoCompleteTextView.setThreshold(1);
                        autoCompleteTextView.setDropDownHeight(400);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("load e ", e.toString());
                        Toast.makeText(AdminSearchDictionary.this, "Failed Load!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void getDictionaryByEn(View view) {
        final String txtAutoComplete = autoCompleteTextView.getText().toString();
        tvTrans2.setText("");
        dictionaryList = new ArrayList<>();
        MainActivity.learningskid.collection(FOLDER_TASK).whereEqualTo(ENGLISH,txtAutoComplete).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        for (DocumentSnapshot doc : documentSnapshots) {
                            Dictionary dictionary = new Dictionary(doc.getString(ID),doc.getString(VIETNAM), doc.getString(ENGLISH),
                                    doc.getString(EXAMPLE));
                            dictionaryList.add(dictionary);
                            if(dictionaryList.size() > 0){
                                tvTrans2.setText("Translate of " + txtAutoComplete + ":" + "\n" + "\n" +
                                        "Means: " + doc.getString(VIETNAM) + "\n" + "\n" + "Other Meanings: " + doc.getString(EXAMPLE));
                            }
                        }
                        if(dictionaryList.size() == 0) {
                            tvTrans2.setText("Your search is not available!");
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("load e ", e.toString());
                        Toast.makeText(AdminSearchDictionary.this, "Failed Load!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void goAcceptDictionary (View v){
        Intent intent = new Intent(getApplicationContext(),AdminAcceptDictionary.class);
        startActivity(intent);
    }
    public void backAHContent(View view){

        Intent intent = new Intent(getApplicationContext(),AdminHomeContent.class);
        intent.putExtra("admin",uName);
        startActivity(intent);
    }
}
