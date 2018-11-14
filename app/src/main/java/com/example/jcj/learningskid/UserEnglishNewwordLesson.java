package com.example.jcj.learningskid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserEnglishNewwordLesson extends AppCompatActivity {
    private SQLiteHelper myHelper = null;
    private ListView listLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_newword_lesson);
        listLesson = findViewById(R.id.listWordLesson);
        myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        myHelper.getReadableDatabase();
        AdapterUser_englishNewword_lesson adapter = new AdapterUser_englishNewword_lesson(this, select());
        listLesson.setAdapter((AdapterUser_englishNewword_lesson)adapter);
    }
    public List<EnglishNewwordLesson> select(){
        List<EnglishNewwordLesson> list = new ArrayList<>();
        if(myHelper == null){
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);

        }
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("UserEnglishNewwordLesson", new String[]{"id", "name"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            EnglishNewwordLesson lesson = new EnglishNewwordLesson(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")));
            list.add(lesson);
        }
        return list;
    }

    public void backToEnglishFromLesson(View view){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContent.class);

        startActivity(intent);
        finish();
    }
}
