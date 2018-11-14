package com.example.jcj.learningskid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEnglishContentGrammarAdd extends AppCompatActivity {
    private SQLiteHelper myHelper = null;
    private EditText etName;
    private EditText etContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_content_grammar_add);
        etName = findViewById(R.id.editGrammarName);
        etContent = findViewById(R.id.editGrammarContent);
        myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        myHelper.getReadableDatabase();
    }

    public void add(View view) {
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
        if(etName.getText().toString().equals("")||etContent.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Can't be blank", Toast.LENGTH_SHORT).show();
        }else {
            SQLiteDatabase db = myHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", etName.getText().toString());
            values.put("content", etContent.getText().toString());
            db.insert("UserEnglishContentGrammar", null, values);
            db.close();
            Toast.makeText(getApplicationContext(), "Add successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarList.class);
            finish();
            startActivity(intent);
        }




    }
}
