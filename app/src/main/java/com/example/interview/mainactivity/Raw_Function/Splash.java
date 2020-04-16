package com.example.interview.mainactivity.Raw_Function;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.interview.R;
import com.example.interview.mainactivity.Main_Category_and_API_Client.MainActivity;

public class Splash extends AppCompatActivity {
    private ImageView imageView;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=(ImageView)findViewById(R.id.splashicon);
        linearLayout=(LinearLayout) findViewById(R.id.ll);
        //Splash Animation
        Animation myanim=AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        linearLayout.startAnimation(myanim);
        //Mirror Navigation Bar
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationBar));
        }

        //Splash fullscreen
        try{
            this.getSupportActionBar().hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }catch (Exception e){

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences("my_prf", MODE_PRIVATE);
                String restoredText = prefs.getString("name", null);
                if (restoredText != null) {
                    Intent i=new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Intent i=new Intent(Splash.this,Intro.class);
                    startActivity(i);
                    finish();
                }

            }
        },3000);
    }
}
