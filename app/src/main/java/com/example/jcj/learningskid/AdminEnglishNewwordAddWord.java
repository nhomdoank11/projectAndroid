package com.example.jcj.learningskid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEnglishNewwordAddWord extends AppCompatActivity {
    private EditText lessonid;
    private EditText word;
    private EditText detail;
    private SQLiteHelper myHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_newword_add_word);
        lessonid = findViewById(R.id.editTextlessonid);
        word = findViewById(R.id.editTextword);
        detail = findViewById(R.id.editTextdetail);
        myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        myHelper.getReadableDatabase();
    }
    public void addword(View v){
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
        if(lessonid.getText().toString().equals("")||word.getText().toString().equals("")||detail.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Can't be blank", Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase db = myHelper.getWritableDatabase();
            ContentValues valuesword = new ContentValues();
            valuesword.put("lessonid", Integer.valueOf(lessonid.getText().toString()));
            valuesword.put("word", word.getText().toString());
            valuesword.put("detail", detail.getText().toString());
            db.insert("UserEnglishNewwordContent", null, valuesword);
            db.close();
            Toast.makeText(getApplicationContext(), "Add successfully", Toast.LENGTH_SHORT).show();
        }
    }
    public void backAdminenglish(View v){
        Intent intent2 = new Intent(getApplicationContext(),AdminEnglishNewword.class);
        startActivity(intent2);
        finish();
    }
}
