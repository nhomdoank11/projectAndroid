package com.example.jcj.learningskid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserEnglishContentGrammarHelper extends SQLiteOpenHelper {
    private  String dbName;
    public UserEnglishContentGrammarHelper(Context context, String dbName, int version){
        super(context,dbName,null,version);
        this.dbName = dbName;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE UserEnglishContentGrammar(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "content text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion != oldVersion){
            String sql = "DROP TABLE UserEnglishContentGrammar";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
