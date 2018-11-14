package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminEnglishNewword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_newword);
    }
    public void gotoaddlesson(View v){
        Intent intentadminlesson = new Intent(getApplicationContext(), AdminEnglishNewwordAddLesson.class);
        startActivity(intentadminlesson);
        finish();
    }
    public void gotoaddnewword(View v){
        Intent intent1 = new Intent(getApplicationContext(),AdminEnglishNewwordAddWord.class);
        startActivity(intent1);
        finish();
    }

}
