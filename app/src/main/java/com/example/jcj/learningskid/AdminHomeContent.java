package com.example.jcj.learningskid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminHomeContent extends AppCompatActivity {

    String NameHolder;
    TextView Name;
    Button LogOUT;
    String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_content);

        Name = findViewById(R.id.textView2);
        LogOUT = findViewById(R.id.button2);
        final Intent intent = getIntent();
        uName = intent.getStringExtra("admin");
        Name.setText(Name.getText().toString() + uName);
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AdminHomeContent.this, "Log Out Successfuly", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goEnglish(View v){
        Intent intent = new Intent(getApplicationContext(), AdminEnglishContent.class);

        startActivity(intent);
        finish();
    }
    public void goSearchDictionary(View view){

        Intent intent = new Intent(getApplicationContext(),AdminSearchDictionary.class);
        intent.putExtra("uName", uName);
        startActivity(intent);
        finish();
    }

}
