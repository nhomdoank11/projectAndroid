package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
public class UserEnglishContentGrammarQuizTest extends AppCompatActivity {
    private EnglishGrammarQuiz exam = new EnglishGrammarQuiz();
    public int i;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private TextView textViewEngGramTest;
    private final String[] title = {"How often do you play tennis?","Where do you usually eat lunch?","How long did you study last night?"};
    private final String[][] names = {{"On Tuesday.","For two hours","Almost every day","With John" },{"Sandwich","With Jane","At 12:00","In the cafeteria"},{"With Bob","In my room","English","For three hours"}};
    private final String[] answer = {"Almost every day","In the cafeteria","For three hours"};
    private String[] getAnswerQuiz = {"","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_content_grammar_quiz_test);

        exam = (EnglishGrammarQuiz) getIntent()
                .getBundleExtra("bundle")
                .getSerializable("exam");

        textViewEngGramTest = findViewById(R.id.textViewEngGramTest);
        radioButton = findViewById(R.id.radioQuizEnglishgrammarA);
        radioButton2 = findViewById(R.id.radioQuizEnglishgrammarB);
        radioButton3 = findViewById(R.id.radioQuizEnglishgrammarC);
        radioButton4 = findViewById(R.id.radioQuizEnglishgrammarD);
        i=0;
        textViewEngGramTest.setText(title[i]);
        radioButton.setText(names[i][0]);
        radioButton2.setText(names[i][1]);
        radioButton3.setText(names[i][2]);
        radioButton4.setText(names[i][3]);

    }
    public void back(View v){
        Intent intent = new Intent(this,UserEnglishContentGrammarQuiz.class);
        startActivity(intent);
        finish();
    }

    public void next(View v){

        if(radioButton.isChecked()){
            getAnswerQuiz[i] = radioButton.getText().toString();
        }else if(radioButton2.isChecked()){
            getAnswerQuiz[i] = radioButton2.getText().toString();
        }else if(radioButton3.isChecked()){
            getAnswerQuiz[i] = radioButton3.getText().toString();
        }else if(radioButton4.isChecked()){
            getAnswerQuiz[i] = radioButton4.getText().toString();
        }

        Toast.makeText(this, getAnswerQuiz[i], Toast.LENGTH_SHORT).show();


        if(i<title.length-1)
            i++;
        textViewEngGramTest.setText(title[i]);
        radioButton.setText(names[i][0]);
        radioButton2.setText(names[i][1]);
        radioButton3.setText(names[i][2]);
        radioButton4.setText(names[i][3]);


    }
    public void pre(View v){
        if(radioButton.isChecked()){
            getAnswerQuiz[i] = radioButton.getText().toString();
        }else if(radioButton2.isChecked()){
            getAnswerQuiz[i] = radioButton2.getText().toString();
        }else if(radioButton3.isChecked()){
            getAnswerQuiz[i] = radioButton3.getText().toString();
        }else if(radioButton4.isChecked()){
            getAnswerQuiz[i] = radioButton4.getText().toString();
        }
        Toast.makeText(this, getAnswerQuiz[i], Toast.LENGTH_SHORT).show();
        if(i>0)
            i--;
        textViewEngGramTest.setText(title[i]);
        radioButton.setText(names[i][0]);
        radioButton2.setText(names[i][1]);
        radioButton3.setText(names[i][2]);
        radioButton4.setText(names[i][3]);
    }
    public void submit(View v){
        double point = 0;
        for(int j = 0 ; j < title.length;j++){
            if(getAnswerQuiz[j].equals(answer[j])){
                point++;
            }
        }
        double check = point/title.length*100;
        check = Math.round(check);
        Toast.makeText(this, String.valueOf(check)+"%", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,UserEnglishContentGrammarQuizResult.class);
//        intent.putExtra("gramResult",String.valueOf(check));
        intent.putExtra("gramResult",check);
        if(exam.getPassCondition()<=check){
            intent.putExtra("gramResultPass","Pass");
        }else {
            intent.putExtra("gramResultPass","Fail");
        }
        startActivity(intent);
        finish();
    }

}
