package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPasswordActivity extends AppCompatActivity {

    EditText etOldPassword, etNewPassword, etConfirmNewPassword;
    Button btnSave;
    DatabaseHelper DB;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        btnSave = findViewById(R.id.btnSave);

        DB = new DatabaseHelper(this);

        storeData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = etOldPassword.getText().toString();
                String newPassword = etNewPassword.getText().toString();
                String confirmNewPassword = etConfirmNewPassword.getText().toString();

                if(oldPassword.equals("") || newPassword.equals("") || confirmNewPassword.equals("")) {
                    Toast.makeText(EditPasswordActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!password.equals(oldPassword)) {
                        Toast.makeText(EditPasswordActivity.this, "Incorrect old password!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!newPassword.equals(confirmNewPassword)) {
                            Toast.makeText(EditPasswordActivity.this, "New passwords do not match!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(password.equals(newPassword)) {
                                Toast.makeText(EditPasswordActivity.this, "Use a different password!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                SharedPreferences prefs = getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
                                String currentUser = prefs.getString("username", "user");
                                boolean insert = DB.editPassword(currentUser, newPassword);
                                if(insert) {
                                    Toast.makeText(EditPasswordActivity.this, "Successfully changed password!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(EditPasswordActivity.this, "Password was not changed successfully.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    void storeData () {
        SharedPreferences prefs = getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String currentUser = prefs.getString("username", "user");
        Cursor cursor = DB.readCurrentUser(currentUser);
        if(cursor.getCount() == 0) {
            Toast.makeText(EditPasswordActivity.this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                password = cursor.getString(2);
            }
        }
    }
}