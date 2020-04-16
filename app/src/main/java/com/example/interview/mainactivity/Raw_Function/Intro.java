package com.example.interview.mainactivity.Raw_Function;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.os.Bundle;

import com.example.interview.R;
import com.example.interview.mainactivity.Main_Category_and_API_Client.MainActivity;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Intro extends AppIntro {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("Welcome to intro", "you can learn interview",
                R.drawable.group, ContextCompat.getColor(getApplicationContext(),R.color.intro1)));
        setZoomAnimation();

        addSlide(AppIntroFragment.newInstance("Welcome to intro", "you can learn interview",
                R.drawable.location, ContextCompat.getColor(getApplicationContext(),R.color.intro2)));
        setFlowAnimation();

        addSlide(AppIntroFragment.newInstance("Welcome to intro", "you can learn interview",
                R.drawable.group, ContextCompat.getColor(getApplicationContext(),R.color.intro3)));
        setFadeAnimation();

        sharedPreferences=getApplicationContext().getSharedPreferences("Mypreference",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if (sharedPreferences!=null){
            boolean checkShared=sharedPreferences.getBoolean("checkstate",false);
            if (checkShared==true){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

        editor.putBoolean("checkstate",false).commit();

        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        startActivity(new Intent(getApplicationContext(),MainActivity.class));

        editor.putBoolean("checkstate",true);
        finish();
    }
}
