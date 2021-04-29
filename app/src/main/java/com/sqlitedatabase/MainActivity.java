package com.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper helper;
    private EditText etFName,etLName,etMarks;
    private Button insert, view, next;
    private  String fName,lName,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DatabaseHelper(this);

        initialise();
        this.insert.setOnClickListener(this::saveData);
    }

    private void saveData(View view) {

        fName=etFName.getText().toString();
        lName=etLName.getText().toString();
        marks=etMarks.getText().toString();

        boolean isInserted = helper.insertData(fName,lName,marks);
        if (isInserted == true){
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();
            etFName.setText(null);
            etLName.setText(null);
            etMarks.setText(null);
        }
        else {
            Toast.makeText(this,"Insertion Failed",Toast.LENGTH_SHORT).show();
        }
    }
    private void initialise() {
        etFName = findViewById(R.id.et_First_Name);
        etLName = findViewById(R.id.et_Last_Name1);
        etMarks = findViewById(R.id.et_Mark1);
        insert = findViewById(R.id.btn_Insert);
        view = findViewById(R.id.btn_View);
        next = findViewById(R.id.btn_View_Data);
    }
}