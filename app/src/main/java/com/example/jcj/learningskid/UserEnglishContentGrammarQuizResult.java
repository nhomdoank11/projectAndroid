package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserEnglishContentGrammarQuizResult extends AppCompatActivity {
    private TextView txtScore;
    private TextView txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_grammar_quiz_result);

        txtScore = findViewById(R.id.txtScore);
        txtPass = findViewById(R.id.txtpassOrFail);
        Intent intent =getIntent();
        double result =  intent.getDoubleExtra("gramResult",0);
        String pass = intent.getStringExtra("gramResultPass");
        txtScore.setText(String.valueOf(result));
        if(pass.equals("Pass")){
            txtPass.setText("congratulation");
        }else if(pass.equals("Fail")){
            txtPass.setText("try again!");
        }


    }
    public void backToGrammarQuiz(View view){
        Intent intent = new Intent(this,UserEnglishContentGrammarQuiz.class);

        startActivity(intent);
        finish();
    }
}
