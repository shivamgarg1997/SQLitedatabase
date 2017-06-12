package com.example.shivam.sqliteapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.button;
import static android.R.attr.name;
import static android.provider.Contacts.SettingsColumns.KEY;

public class OrderActivity extends AppCompatActivity {
    DBhelper myDb;
    EditText quantity;
    Spinner firm;
    Button book,orders,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        firm = (Spinner) findViewById(R.id.firm);
        book = (Button) findViewById(R.id.book);
        orders = (Button) findViewById(R.id.orders);
        logout = (Button) findViewById(R.id.logout);
        quantity = (EditText) findViewById(R.id.quantity);
        Intent in = getIntent();
        final String id = in.getExtras().getString("name");
        final String password = in.getExtras().getString("pass");
        List<String> lables = myDb.getAllNames();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firm.setAdapter(dataAdapter);
        final String text = firm.getSelectedItem().toString();
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean insert = myDb.insertData(text, quantity.getText().toString() , id);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer orders = myDb.getorders(id , password);
                orders.toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setCancelable(true);
                builder.setTitle("orders");
                builder.setMessage(orders);
                builder.show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderActivity.this);
                alertDialogBuilder.setMessage("Are you sure");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Intent in = new Intent(OrderActivity.this,MainActivity.class);
                                        startActivity(in);
                                        finish();
                                    }
                                });
                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}

