package com.example.shivam.sqliteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DBhelper myDb;
    Button login;
    EditText name,pass;

    public static final String MyPref = "MyPref" ;
    public static final String MyLogin = "Log" ;
    public static final String MyPass= "pass" ;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DBhelper(this);
        login = (Button) findViewById(R.id.login);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        pref = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getdata(name.getText().toString() , pass.getText().toString());
                if(res.getCount() ==0 ){
                    Toast.makeText(LoginActivity.this,
                            "Login Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(MyLogin, name.getText().toString());
                    editor.putString(MyPass, pass.getText().toString());
                    editor.commit();
                    String id = name.getText().toString();
                    String password = pass.getText().toString();
                    Intent in = new Intent(LoginActivity.this,OrderActivity.class);
                    in.putExtra("name",id);
                    in.putExtra("pass",password);
                    startActivity(in);
                    finish();
                }
            }
        });
    }
}
