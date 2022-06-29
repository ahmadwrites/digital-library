package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetStartedActivity extends AppCompatActivity {

    Button btnGetStarted;
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        btnGetStarted = findViewById(R.id.btnGetStarted);
        txtLogin = findViewById(R.id.txtLogin);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetStartedActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}