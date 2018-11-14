package com.example.jcj.learningskid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEnglishContentGrammarQuizAdd extends AppCompatActivity {

    private SQLiteHelper myHelper = null;
    private EditText etName;
    private EditText etCondition;
    private EditText etStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_content_grammar_quiz_add);
        etName = findViewById(R.id.editGrammarQuizName);
        etCondition = findViewById(R.id.editGrammarQuizPass);
        etStatus = findViewById(R.id.editGrammarQuizStatus);
        myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        myHelper.getReadableDatabase();
    }

    public void add(View view) {
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
        if(etName.getText().toString().equals("")||etCondition.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Can't be blank", Toast.LENGTH_SHORT).show();
        }else {
            SQLiteDatabase db = myHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", etName.getText().toString());
            values.put("passCondition", etCondition.getText().toString());
            values.put("status", etStatus.getText().toString());
            db.insert("UserEnglishContentGrammarQuiz", null, values);
            db.close();
            Toast.makeText(getApplicationContext(), "Add successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammarQuiz.class);
            finish();
            startActivity(intent);
        }




    }

}
