package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserHomeContent extends AppCompatActivity {


    TextView Name;
    Button LogOUT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_content);

        Name = findViewById(R.id.textView1);
        Intent intent = getIntent();
        String uName = intent.getStringExtra("user");
        Name.setText(Name.getText().toString()+ uName);
//        LogOUT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Toast.makeText(UserHomeContent.this,"Log Out Successfuly", Toast.LENGTH_LONG).show();
//            }
//        });

    }
    public void goUserEnglishContent(View v){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContent.class);

        startActivity(intent);
        finish();
    }

}
