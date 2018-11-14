package com.example.jcj.learningskid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.UUID;

public class UserDictionary extends AppCompatActivity {
    private EditText edtVietNam,edtEnglish,edtExample;

    String UID = UUID.randomUUID().toString();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dictionary);
        edtVietNam = findViewById(R.id.edTVietNam);
        edtEnglish = findViewById(R.id.edTEnglish);
        edtExample = findViewById(R.id.edtExample);
    }

    public void addnew(View view){

        Dictionary valuses = new Dictionary();
        valuses.setId(UID);
        valuses.setVietNam(edtVietNam.getText().toString());
        valuses.setEnglish(edtEnglish.getText().toString());
        valuses.setExample(edtExample.getText().toString());
        MainActivity.learningskid.collection("Dictionary").document(UID).set(valuses)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UserDictionary.this,"Your request was submitted successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),UserSearchDictionary.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserDictionary.this,"ERROR!",Toast.LENGTH_LONG).show();
                        Log.d(MainActivity.TAG,e.toString());
                    }
                });
    }
    public void  backToUserSearchDic(View v){
        Intent intent = new Intent(getApplicationContext(),UserSearchDictionary.class);
        startActivity(intent);
        finish();
    }


}
