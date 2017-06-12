package com.example.shivam.sqliteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DBhelper myDb;
    Button sign_up;
    EditText name,pass,phone_no;

    public static final String MyPref = "MyPref" ;
    public static final String MyLogin = "Log" ;
    public static final String MyPass= "pass" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        phone_no = (EditText) findViewById(R.id.phno);
        sign_up = (Button) findViewById(R.id.signup);
        sharedpreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean insert = myDb.insertData(name.getText().toString() , pass.getText().toString() , phone_no.getText().toString());
                if(insert){
                    Toast.makeText(SignupActivity.this,
                            "Signing In", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(MyLogin, name.getText().toString());
                    editor.putString(MyPass, pass.getText().toString());
                    editor.commit();
                    Intent in = new Intent(SignupActivity.this,OrderActivity.class);
                    startActivity(in);
                    finish();
                }
                else{
                    Toast.makeText(SignupActivity.this,
                            "Signup Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
