package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminAcceptDictionary extends AppCompatActivity {

    ListView lvDicProduct;
    TextView tvAcceptDic;
    private List<Dictionary> dictionaryList3 = new ArrayList<>();
    private static final String ID = "id";
    private static final String VIETNAM = "vietNam";
    private static final String ENGLISH = "english";
    private static final String EXAMPLE = "example";
    private static final String FOLDER_TASK = "Dictionary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accept_dictionary);
        lvDicProduct = findViewById(R.id.lvDicProduct);
        tvAcceptDic = findViewById(R.id.tvAcceptDic);
        getAllUDictionary();
    }

    public void getAllUDictionary(){
        final String a = "Các từ mới cần được duyệt";
            dictionaryList3 = new ArrayList<>();
            MainActivity.learningskid.collection("Dictionary").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for (DocumentSnapshot doc : documentSnapshots) {
                                Dictionary dictionary = new Dictionary(doc.getString(ID),doc.getString(VIETNAM), doc.getString(ENGLISH),
                                        doc.getString(EXAMPLE));
                                dictionaryList3.add(dictionary);
                            }
                            AdapterAdmin_Dictionary adapter = new AdapterAdmin_Dictionary(AdminAcceptDictionary.this, dictionaryList3);
                            lvDicProduct.setAdapter(adapter);
                            if(dictionaryList3.size() < 0){
                                tvAcceptDic.setText("không có từ mới nào cần được duyệt");
                            } else {
                                tvAcceptDic.setText("Các từ mới cần được duyệt");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("load e ", e.toString());
                            Toast.makeText(AdminAcceptDictionary.this, "Failed Load!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        public void backASDictionary(View view){

            Intent intent = new Intent(getApplicationContext(),AdminSearchDictionary.class);
            startActivity(intent);
            finish();
        }
}
