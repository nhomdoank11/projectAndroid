package com.example.jcj.learningskid;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, name , password2;
    Button register;
    String UID = UUID.randomUUID().toString();
    private List<User> usersListU = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.buttonRegister);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        password2 = findViewById(R.id.editRPassword2);
        name = findViewById(R.id.editName);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString() == null){
                    Toast.makeText(getApplicationContext(),"Email không được để trống",Toast.LENGTH_LONG).show();
                } else if(name.getText().toString() == null){
                    Toast.makeText(getApplicationContext(),"UserName không được để trống",Toast.LENGTH_LONG).show();
                } else if(password.getText().toString() == null){
                    Toast.makeText(getApplicationContext(),"PassWord không được để trống",Toast.LENGTH_LONG).show();
                }else if(password.length() < 8 || password.length() > 16){
                    Toast.makeText(getApplicationContext(),"Mật khẩu phải có nhiều hơn 8 và ít hơn 16 kí tự",Toast.LENGTH_LONG).show();
                } else if(!password.getText().toString().equals(password2.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Mật khẩu không giống nhau",Toast.LENGTH_SHORT).show();
                } else{
                    final String txtUser = name.getText().toString();
                    usersListU = new ArrayList<>();
                    MainActivity.learningskid.collection("User").whereEqualTo("userName",txtUser).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                    for (DocumentSnapshot doc : documentSnapshots) {
                                        User userList = new User(doc.getString("id"),doc.getString("userName"), doc.getString("passWord"),
                                                doc.getString("email"));
                                        usersListU.add(userList);
                                    }
                                    if(usersListU.size() > 0){
                                        Toast.makeText(getApplicationContext(),"Tên người dùng đã được sử dụng",Toast.LENGTH_LONG).show();
                                    } else {
                                        User valusesU = new User();
                                        valusesU.setId(UID);
                                        valusesU.setEmail(email.getText().toString());
                                        valusesU.setPassWord(password.getText().toString());
                                        valusesU.setUserName(name.getText().toString());
                                        MainActivity.learningskid.collection("User").document(UID).set(valusesU)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(RegisterActivity.this,"Bạn đã tạo tài khoản thành công",Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(RegisterActivity.this,"Lỗi!",Toast.LENGTH_LONG).show();
                                                        Log.d(MainActivity.TAG,e.toString());
                                                    }
                                                });
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("load e ", e.toString());
                                    Toast.makeText(RegisterActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                                }
                            });


                }

            }
        });

    }
    public void getBackLogin(View view) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
    }

//    // SQLite database build method.
//    public void SQLiteDataBaseBuild() {
//
//        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.dbName, Context.MODE_PRIVATE, null);
//
//    }
//
//    // SQLite table build method.
//    public void SQLiteTableBuild() {
//
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.tableName + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR);");
//
//    }
//
//    public void InsertDataIntoSQLiteDatabase() {
//
//        if (EditTextEmptyHolder == true) {
//
//            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.tableName + " (name,email,password) VALUES('" + NameHolder + "', '" + EmailHolder + "', '" + PasswordHolder + "');";
//            sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
//            sqLiteDatabase.close();
//
//            Toast.makeText(RegisterActivity.this, "User Registered Successfully. Back to login screen to login", Toast.LENGTH_LONG).show();
//
//        }
//        else {
//
//            Toast.makeText(RegisterActivity.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
//
//        }
//    }
//
//    public void EmptyEditTextAfterDataInsert() {
//        Name.getText().clear();
//        Email.getText().clear();
//        Password.getText().clear();
//    }
//
//    public void CheckEditTextStatus() {
//
//        NameHolder = Name.getText().toString();
//        EmailHolder = Email.getText().toString();
//        PasswordHolder = Password.getText().toString();
//
//        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
//
//            EditTextEmptyHolder = false;
//
//        } else {
//
//            EditTextEmptyHolder = true;
//        }
//    }
//
//    public void CheckingNameAlreadyExistsOrNot() {
//
//        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
//
//        // Adding search name query to cursor.
//        cursor = sqLiteDatabase.query(SQLiteHelper.tableName, null, " " + SQLiteHelper.Table_Column_1_Name + "=?", new String[]{NameHolder}, null, null, null);
//
//        while (cursor.moveToNext()) {
//            if (cursor.isFirst()) {
//                cursor.moveToFirst();
//                F_Result = "Name Found";
//                cursor.close();
//            }
//        }
//
//        CheckFinalResult();
//
//    }
//
//    public void CheckFinalResult() {
//
//        // Checking whether name is already exists or not.
//        if (F_Result.equalsIgnoreCase("Name Found")) {
//
//            Toast.makeText(RegisterActivity.this, "Username Already Exists", Toast.LENGTH_LONG).show();
//
//        } else {
//
//            InsertDataIntoSQLiteDatabase();
//
//        }
//
//        F_Result = "Not_Found";
//
//    }
}
