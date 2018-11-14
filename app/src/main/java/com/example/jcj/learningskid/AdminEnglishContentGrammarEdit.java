package com.example.jcj.learningskid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEnglishContentGrammarEdit extends AppCompatActivity {
    private EditText etName;
    private EditText etDescription;
    private SQLiteHelper myHelper = null;
    private EnglishGrammar exam = new EnglishGrammar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_content_grammar_edit);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        exam = (EnglishGrammar) getIntent()
                .getBundleExtra("bundle")
                .getSerializable("exam");
        if (exam != null) {
            etName.setText(exam.getName());
            etDescription.setText(exam.getContent());
        }
    }

    public void update(View view) {
        if(myHelper == null){
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
        if(etName.getText().toString().equals("")||etDescription.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Can't be blank", Toast.LENGTH_SHORT).show();
        }else {
            SQLiteDatabase db = myHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", etName.getText().toString());
            values.put("content", etDescription.getText().toString());
            db.update("UserEnglishContentGrammar",values,"id=?", new String[]{String.valueOf(exam.getId())});
            db.close();
            Toast.makeText(getApplicationContext(),"Update successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarList.class);
            finish();
            startActivity(intent);
        }

    }
}
