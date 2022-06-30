package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CollectionDetailActivity extends AppCompatActivity {

    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

        String title = getIntent().getStringExtra("title");

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(title);

    }
}