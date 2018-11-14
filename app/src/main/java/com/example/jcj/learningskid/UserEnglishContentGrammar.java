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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserEnglishContentGrammar extends AppCompatActivity {
    private SQLiteHelper myHelper = null;
    private ListView listviewUserEnglishGrammar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_content_grammar);
        listviewUserEnglishGrammar = findViewById(R.id.listviewUserEnglishGrammar);
        myHelper = new SQLiteHelper(getApplicationContext(),"LearningKid",1);
        myHelper.getReadableDatabase();


        AdapterUser_english_content myAdapter = new AdapterUser_english_content(this, select());
        listviewUserEnglishGrammar.setAdapter(myAdapter);
//        registerForContextMenu(listviewUserEnglishGrammar);

    }
    public List<EnglishGrammar> select() {
        List<EnglishGrammar> list = new ArrayList<>();
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
//        myHelper = new UserEnglishContentGrammarHelper(getApplicationContext(), "LearningKid", 1);
        SQLiteDatabase db = myHelper.getReadableDatabase();
        try{
            Cursor cursor = db.query("UserEnglishContentGrammar", new String[]{"id", "name", "content"},
                    null, null
                    , null, null, null);
            while (cursor.moveToNext()) {
                EnglishGrammar student = new EnglishGrammar(cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("content")));
                list.add(student);
            }
        }catch (Exception e){
//            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

        return list;
    }
    public void logout(View v){

    }
    public void quiz(View view){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammarQuiz.class);

        startActivity(intent);
        finish();
    }
    public void backToEnglish(View view){
        Intent intent = new Intent(getApplicationContext(), UserEnglishContent.class);

        startActivity(intent);
        finish();
    }
//    //test add phan admin
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add("Add");
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        String title = String.valueOf(item.getTitle());
//        switch (title) {
//            case "Add":
//                Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarAdd.class);
//                finish();
//                startActivity(intent);
//
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
