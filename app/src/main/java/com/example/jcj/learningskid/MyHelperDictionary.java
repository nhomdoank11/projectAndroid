package com.example.jcj.learningskid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelperDictionary extends SQLiteOpenHelper {

    MyHelperDictionary(Context context, String dbName, int version){
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Dictionary("+
                "id integer primary key autoincrement,"+
                "vietNam text not null,"+
                "english text not null,"+
                "example text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion){
            String sql = "DROP TABLE Dictionary";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}

