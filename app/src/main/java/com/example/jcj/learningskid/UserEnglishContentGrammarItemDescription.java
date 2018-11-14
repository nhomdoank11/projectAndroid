package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserEnglishContentGrammarItemDescription extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewContent;
    private EnglishGrammar exam = new EnglishGrammar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_content_grammar_item_description);

        textViewName = findViewById(R.id.textViewName);
        textViewContent = findViewById(R.id.textViewContent);
        exam = (EnglishGrammar) getIntent()
                .getBundleExtra("bundle")
                .getSerializable("exam");
        if (exam != null) {
            textViewName.setText(exam.getName());
            textViewContent.setText(exam.getContent());
        }
    }
    public void back(View v){
        Intent intent1 = new Intent(getApplicationContext(), UserEnglishContentGrammar.class);

        startActivity(intent1);
        finish();
    }
}
