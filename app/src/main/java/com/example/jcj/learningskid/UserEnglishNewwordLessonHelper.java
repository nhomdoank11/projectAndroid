package com.example.jcj.learningskid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserEnglishNewwordLessonHelper extends SQLiteOpenHelper {
    private String dbName;
    public UserEnglishNewwordLessonHelper(Context context, String dbName, int version){
        super(context, dbName, null, version);
        this.dbName = dbName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE UserEnglishNewwordLesson(" +
                "id integer primary key autoincrement," +
                "name text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
