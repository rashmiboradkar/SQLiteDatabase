package com.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etemail,etpass;
    private Button login;
    private String email,pass;
    SQLiteDatabase database;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        helper = new DatabaseHelper(this);
        database = helper.getWritableDatabase();
        initialise();
        this.login.setOnClickListener(this::fetchData);
    }

    private void fetchData(View view) {
        email = etemail.getText().toString();
        pass = etpass.getText().toString();

        String storedPassword= getSingleEntry(email);
        if (!email.equals("") && !pass.equals("")){
            if (pass.equals(storedPassword)){
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this,"Email & Password dont match",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
        }
    }

    private void initialise() {
        etemail =findViewById(R.id.et_Email_Login);
        etpass = findViewById(R.id.et_pass_login);

        login = findViewById(R.id.btn_login);
    }

    public String getSingleEntry(String email) {

        String myEmail = DatabaseHelper.COL_5 + "=?";
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, myEmail, new String[]{email}, null, null, null);

        if (cursor.getCount() < 1) {
            cursor.close();
            return "DATA NOT EXISTS";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_6));
        cursor.close();
        return password;
    }
}