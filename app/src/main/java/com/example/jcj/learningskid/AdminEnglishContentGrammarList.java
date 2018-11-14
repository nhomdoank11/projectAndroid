package com.example.jcj.learningskid;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminEnglishContentGrammarList extends AppCompatActivity {
    private SQLiteHelper myHelper = null;
    private ListView listviewUserEnglishGrammar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_english_content_grammar_list);

        listviewUserEnglishGrammar = findViewById(R.id.listviewUserEnglishGrammar);
        myHelper = new SQLiteHelper(getApplicationContext(),"LearningKid",1);
        myHelper.getReadableDatabase();


        AdapterAdmin_english_content myAdapter = new AdapterAdmin_english_content(this, select());
        listviewUserEnglishGrammar.setAdapter(myAdapter);
        registerForContextMenu(listviewUserEnglishGrammar);
    }

    public List<EnglishGrammar> select() {
        List<EnglishGrammar> list = new ArrayList<>();
        if (myHelper == null) {
            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
        }
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("UserEnglishContentGrammar", new String[]{"id", "name", "content"},
                null, null
                , null, null, null);
        while (cursor.moveToNext()) {
            EnglishGrammar student = new EnglishGrammar(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("content")));
            list.add(student);
        }
        return list;
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String title = String.valueOf(item.getTitle());
        switch (title) {
            case "Add":
                Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarAdd.class);
                finish();
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Add");
        menu.add("Edit");
        menu.add("Delete");
    }

    //create context menu
    public boolean onContextItemSelected(MenuItem item) {
        String title = String.valueOf(item.getTitle());
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (title) {
            case  "Add":
                Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarAdd.class);

                startActivity(intent);
                finish();
                break;
            case "Edit":
                EnglishGrammar exam = (EnglishGrammar) listviewUserEnglishGrammar.getItemAtPosition(info.position);
                Intent intent1 = new Intent(getApplicationContext(), AdminEnglishContentGrammarEdit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exam", exam);
                intent1.putExtra("bundle", bundle);
                startActivity(intent1);
                finish();
                break;
            case "Delete":
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("are you sure about this?");
                builder.setTitle("Delete");

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EnglishGrammar exam = (EnglishGrammar) listviewUserEnglishGrammar.getItemAtPosition(info.position);
                        if (myHelper == null) {
                            myHelper = new SQLiteHelper(getApplicationContext(), "LearningKid", 1);
                        }
                        SQLiteDatabase db = myHelper.getWritableDatabase();
                        db.delete("UserEnglishContentGrammar", "id=?", new String[]{String.valueOf(exam.getId())});
                        Toast.makeText(getApplicationContext(), "Delete successfully", Toast.LENGTH_SHORT).show();

                    }
                });
                Adapter myAdapter = new AdapterAdmin_english_content(this, select());
                listviewUserEnglishGrammar.setAdapter((ListAdapter) myAdapter);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
        }
        return super.onContextItemSelected(item);
    }
}
