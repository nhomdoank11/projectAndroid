package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminEnglishContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_content);
    }
    public void goEnglishGrammar(View v){
        Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarList.class);

        startActivity(intent);
        finish();
    }
    public void gotoadminnewword (View v){
        Intent intentadminnewword = new Intent(getApplicationContext(),AdminEnglishNewword.class);
        startActivity(intentadminnewword);
        finish();
    }
}
