package com.example.jcj.learningskid;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
// day la login page
    Button LogInButton, RegisterButton;
    EditText Name, Password;
    String NameHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND";
    public static final String UserName = "";
    public static  final FirebaseFirestore learningskid = FirebaseFirestore.getInstance();
    public static final String TAG = "MainActivity";
    private List<User> usersListLogin = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LogInButton = findViewById(R.id.buttonLogin);
        RegisterButton = findViewById(R.id.buttonRegister);
        Name = findViewById(R.id.editName);
        Password = findViewById(R.id.editPassword);

        sqLiteHelper = new SQLiteHelper(this);

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String uName = Name.getText().toString();
                usersListLogin = new ArrayList<>();
                MainActivity.learningskid.collection("User").whereEqualTo("userName",uName).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot documentSnapshots) {
                                for (DocumentSnapshot doc : documentSnapshots) {
                                    User userList = new User(doc.getString("id"),doc.getString("userName"), doc.getString("passWord"),
                                            doc.getString("email"));
                                    usersListLogin.add(userList);
                                }
                                if(usersListLogin.size() == 0){
                                        Toast.makeText(getApplicationContext(),"Username or password incorrect! ",Toast.LENGTH_LONG).show();
                                } else {
                                    if(uName.equals("admin")){
                                        Intent intent = new Intent(getApplicationContext(), AdminHomeContent.class);
                                        intent.putExtra("admin", uName);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Logged in successfully!",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), UserHomeContent.class);
                                        intent.putExtra("user", uName);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Logged in successfully!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("load e ", e.toString());
                                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    
        
    public void LoginFunction() {

        if (EditTextEmptyHolder) {

            sqLiteDatabase = sqLiteHelper.getWritableDatabase();

            
            cursor = sqLiteDatabase.query(SQLiteHelper.tableName, null, " " + SQLiteHelper.Table_Column_1_Name + "=?", new String[]{NameHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered name.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                    cursor.close();
                }
            }

            CheckFinalResult();

        } else {

            Toast.makeText(MainActivity.this, "Please Enter Username or Password.", Toast.LENGTH_LONG).show();

        }
    }

    
    public void CheckEditTextStatus() {

        NameHolder = Name.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    public void CheckFinalResult() {
        if(NameHolder.equals("admin") && PasswordHolder.equals("admin") ) {
            Intent intent = new Intent(MainActivity.this, AdminHomeContent.class);
            intent.putExtra(UserName, NameHolder);
            startActivity(intent);

        }
        if (TempPassword.equalsIgnoreCase(PasswordHolder)) {

            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, UserHomeContent.class);
            intent.putExtra(UserName, NameHolder);
            startActivity(intent);


        } else {
            Toast.makeText(MainActivity.this, "UserName or Password is Wrong, Please Try Again.", Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND";
    }

//    public void demoGrammar(View v){
//        Intent intent = new Intent(getApplicationContext(), UserEnglishContentGrammar.class);
//
//        startActivity(intent);
//        finish();
//    }
//    public void demoAdminGrammar(View v){
//        Intent intent = new Intent(getApplicationContext(), AdminEnglishContentGrammarList.class);
//
//        startActivity(intent);
//        finish();
//    }
}
