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

public class EditProfileActivity extends AppCompatActivity {

    EditText etPhone;
    Button btnSave;

    DatabaseHelper myDB;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);

        myDB = new DatabaseHelper(this);

        storeData();

        etPhone.setText(phone);

        SharedPreferences prefs = getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String currentUser = prefs.getString("username", "user");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPhone = etPhone.getText().toString();

                if(newPhone.equals("")) {
                    Toast.makeText(EditProfileActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean insert = myDB.editProfile(currentUser, newPhone);
                    if(insert) {
                        Toast.makeText(EditProfileActivity.this, "Successfully edited profile.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(EditProfileActivity.this, "Error editing profile.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    void storeData () {
        SharedPreferences prefs = getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String currentUser = prefs.getString("username", "user");
        Cursor cursor = myDB.readCurrentUser(currentUser);
        if(cursor.getCount() == 0) {
            Toast.makeText(EditProfileActivity.this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                phone = cursor.getString(3);
            }
        }
    }
}