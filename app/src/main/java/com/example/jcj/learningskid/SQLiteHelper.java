package com.example.jcj.learningskid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String dbName = "LearningKid";

    public static final String tableName = "UserTable";
    public static final String Table_Column_ID = "id";
    public static final String Table_Column_1_Name = "name";
    public static final String Table_Column_2_Email = "email";
    public static final String Table_Column_3_Password = "password";

    public SQLiteHelper(Context context) {

        super(context, dbName, null, 1);

    }

    public SQLiteHelper(Context context, String dbName, int version) {

        super(context, dbName, null, 1);
        this.dbName = dbName;
    }
    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + Table_Column_ID + " INTEGER PRIMARY KEY, " + Table_Column_1_Name + " VARCHAR, " + Table_Column_2_Email + " VARCHAR, " + Table_Column_3_Password + " VARCHAR)";
        database.execSQL(CREATE_TABLE);

        String createUserEnglishContentGrammarQuiz = "CREATE TABLE IF NOT EXISTS UserEnglishContentGrammarQuiz(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "passCondition integer ,"+
                "status text)";
        database.execSQL(createUserEnglishContentGrammarQuiz);

        String createUserEnglishContentGrammar = "CREATE TABLE IF NOT EXISTS UserEnglishContentGrammar(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "content text)";
        database.execSQL(createUserEnglishContentGrammar);

        String createUserEnglishNewwordLesson = "CREATE TABLE IF NOT EXISTS UserEnglishNewwordLesson(" +
                "id integer primary key autoincrement," +
                "name text not null)";
        database.execSQL(createUserEnglishNewwordLesson);

        String createUserEnglishNewwordContent = "CREATE TABLE UserEnglishNewwordContent(" +
                "id integer primary key autoincrement," +
                "lessonid integer,"+
                "word text not null,"+
                "detail text not null)";
        database.execSQL(createUserEnglishNewwordContent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion != oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            String sql = "DROP TABLE IF EXISTS UserEnglishContentGrammar";
            db.execSQL(sql);
            sql = "DROP TABLE IF EXISTS UserEnglishContentGrammarQuiz";
            db.execSQL(sql);
            sql = "DROP TABLE IF EXISTS UserEnglishNewwordLesson";
            db.execSQL(sql);
            sql = "DROP TABLE IF EXISTS createUserEnglishNewwordContent";
            db.execSQL(sql);
            onCreate(db);

        }

    }

}
