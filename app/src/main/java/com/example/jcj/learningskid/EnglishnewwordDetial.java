package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EnglishnewwordDetial extends AppCompatActivity {
    private TextView detailword;
    private String detail;
    int lessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_englishnewword_detial);
        detailword = findViewById(R.id.txtdetail);
        Intent intentWorddetail = getIntent();
        detail = intentWorddetail.getStringExtra("Detail");
        lessionId = intentWorddetail.getIntExtra("lessonID",0);
        detailword.setText(detail);

    }
    public void backtonewwordlistfromdetail(View view){
        Intent intentwordlist = new Intent(getApplicationContext(),UserEnglishNewwordLesson.class);

        intentwordlist.putExtra("lessonID", lessionId);
        startActivity(intentwordlist);
        finish();
    }
}
