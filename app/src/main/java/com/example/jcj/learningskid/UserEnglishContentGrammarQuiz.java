package com.example.jcj.learningskid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserEnglishContentGrammarQuiz extends AppCompatActivity {
    private SQLiteHelper myHelper = null;
    private ListView listviewUserEnglishGrammarQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_content_grammar_quiz);
        listviewUserEnglishGrammarQuiz = findViewById(R.id.listviewUserEnglishGrammarQuiz);

        myHelper = new SQLiteHelper(getApplicationContext(),"LearningKid",1);
        myHelper.getReadableDatabase();
        AdapterUser_english_content_grammar_quiz myAdapter = new AdapterUser_english_content_grammar_quiz(this, select());
        listviewUserEnglishGrammarQuiz.setAdapter(myAdapter);
    }
    public List<EnglishGrammarQuiz> select() {
        List<EnglishGrammarQuiz> list = new ArrayList<>();
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
//        myHelper = new UserEnglishContentGrammarHelper(getApplicationContext(), "LearningKid", 1);
        SQLiteDatabase db = myHelper.getReadableDatabase();
        try{
            Cursor cursor = db.query("UserEnglishContentGrammarQuiz", new String[]{"id", "name","passCondition", "status"},
                    null, null
                    , null, null, null);
            while (cursor.moveToNext()) {
                EnglishGrammarQuiz student = new EnglishGrammarQuiz(cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getInt(cursor.getColumnIndex("passCondition")),
                        cursor.getString(cursor.getColumnIndex("status")));
                list.add(student);
            }
        }catch (Exception e){
//            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

        return list;
    }
    public void logout(View v){

    }
    public void back(View v){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammar.class);

        startActivity(intent);
        finish();
    }

    //    //test add phan admin
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String title = String.valueOf(item.getTitle());
        switch (title) {
            case "Add":
                Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarQuizAdd.class);
                finish();
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
