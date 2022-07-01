package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class CollectionDetailActivity extends AppCompatActivity {

    TextView txtTitle, txtAuthor, txtDesc, txtType, txtDate, txtViewed, txtCollectionId;
    RatingBar rbReview;
    Button btnEdit, btnDelete;

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
        txtCollectionId = findViewById(R.id.txtCollectionId);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String desc = getIntent().getStringExtra("desc");
        String type = getIntent().getStringExtra("type");
        String date = getIntent().getStringExtra("date");
        String viewed = getIntent().getStringExtra("viewed");
        String rating = getIntent().getStringExtra("rating");
        String collectionId = getIntent().getStringExtra("collectionId");
        String userId = getIntent().getStringExtra("userId");

        String viewedStatus = "";

        if (viewed.equals("1")) {
            viewedStatus = "Viewed";
        } else {
            viewedStatus = "Not Viewed";
        }

        txtCollectionId.setText(collectionId);
        txtTitle.setText(title);
        txtAuthor.setText(author);
        txtDesc.setText(desc);
        txtType.setText(type);
        txtDate.setText(date);
        txtViewed.setText(viewedStatus);
        rbReview.setRating(Float.parseFloat(rating));

        String finalViewedStatus = viewedStatus;
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollectionDetailActivity.this, EditCollectionActivity.class);
                intent.putExtra("collectionId", collectionId);
                intent.putExtra("userId", userId);
                intent.putExtra("author", author);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                intent.putExtra("type", type);
                intent.putExtra("date", date);
                intent.putExtra("viewed", finalViewedStatus);
                intent.putExtra("rating", rating);
                startActivity(intent);
            }
        });

    }
}