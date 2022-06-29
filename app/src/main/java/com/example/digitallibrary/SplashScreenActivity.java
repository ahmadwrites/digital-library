package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView imgLogo;
    TextView txtTitleAnim, txtSloganAnim;
    Animation bottom_animation, fadein_animation, subtitle_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        imgLogo = findViewById(R.id.imgLogo);
        txtTitleAnim = findViewById(R.id.txtTitleAnim);
        txtSloganAnim = findViewById(R.id.txtSloganAnim);

        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        fadein_animation = AnimationUtils.loadAnimation(this, R.anim.fadein_animation);
        subtitle_animation = AnimationUtils.loadAnimation(this, R.anim.subtitle_animation);

        imgLogo.setAnimation(fadein_animation);
        txtTitleAnim.setAnimation(bottom_animation);
        txtSloganAnim.setAnimation(subtitle_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, GetStartedActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
    }
}