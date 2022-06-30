package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class CollectionDetailActivity extends AppCompatActivity {

    TextView txtTitle, txtAuthor, txtDesc, txtType, txtDate, txtViewed;
    RatingBar rbReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtDesc = findViewById(R.id.txtDesc);
        txtType = findViewById(R.id.txtType);
        txtDate = findViewById(R.id.txtDate);
        txtViewed = findViewById(R.id.txtViewed);
        txtViewed = findViewById(R.id.txtViewed);
        rbReview = findViewById(R.id.rbReview);

        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String desc = getIntent().getStringExtra("desc");
        String type = getIntent().getStringExtra("type");
        String date = getIntent().getStringExtra("date");
        String viewed = getIntent().getStringExtra("viewed");
        String rating = getIntent().getStringExtra("rating");

        if(viewed.equals("1")) {
            viewed = "Yes";
        } else {
            viewed = "No";
        }

        txtTitle.setText(title);
        txtAuthor.setText(author);
        txtDesc.setText(desc);
        txtType.setText(type);
        txtDate.setText(date);
        txtViewed.setText(viewed);
        rbReview.setRating(Float.parseFloat(rating));

    }
}