package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class EditCollectionActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etDesc, etDate, etRating;
    Spinner spType;
    Switch swViewed;
    Button btnUpdate;
    
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_collection);

        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etDesc = findViewById(R.id.etDesc);
        etDate = findViewById(R.id.etDate);
        etRating = findViewById(R.id.etRating);
        spType = findViewById(R.id.spType);
        swViewed = findViewById(R.id.swViewed);
        btnUpdate = findViewById(R.id.btnUpdate);

        DB = new DatabaseHelper(this);

        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String desc = getIntent().getStringExtra("desc");
        String type = getIntent().getStringExtra("type");
        String date = getIntent().getStringExtra("date");
        String viewed = getIntent().getStringExtra("viewed");
        String rating = getIntent().getStringExtra("rating");
        String collectionId = getIntent().getStringExtra("collectionId");
        String userId = getIntent().getStringExtra("userId");

        String[] types = getResources().getStringArray(R.array.type);

        int typeIndex = 0;

        for(int i=0; i < types.length; i++) {
            if(type.equals(types[i])) {
                typeIndex = i;
            }
        }

        etTitle.setText(title);
        etAuthor.setText(author);
        etDesc.setText(desc);
        etDate.setText(date);
        spType.setSelection(typeIndex);
        etRating.setText(rating);
        swViewed.setText(viewed);

        if (swViewed.getText().toString().equals("Viewed")) {
            swViewed.setChecked(true);
        } else {
            swViewed.setChecked(false);
        }

        swViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swViewed.isChecked()) {
                    swViewed.setText("Viewed");
                } else {
                    swViewed.setText("Not Viewed");
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String author = etAuthor.getText().toString();
                String desc = etDesc.getText().toString();
                String date = etDate.getText().toString();
                String type = spType.getSelectedItem().toString();
                String rating = etRating.getText().toString();
                Boolean viewed = swViewed.getText().toString() == "Viewed" ? true : false;

                if (author.equals("") || desc.equals("") || date.equals("") || type.equals("Select item type") || rating.equals("")) {
                    Toast.makeText(EditCollectionActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean update = DB.updateCollection(collectionId, title, author, desc, type, viewed, date, rating);
                    if(update) {
                        Toast.makeText(EditCollectionActivity.this, "Successfully updated collection", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditCollectionActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(EditCollectionActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}