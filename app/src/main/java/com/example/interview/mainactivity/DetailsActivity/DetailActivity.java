package com.example.interview.mainactivity.DetailsActivity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.interview.R;

public class DetailActivity extends AppCompatActivity {
    WebView ContentTextWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ContentTextWeb=(WebView)findViewById(R.id.contentText);

        Intent intent=getIntent();

        String name=intent.getStringExtra("content");


        ContentTextWeb.loadData(name,"text/html; charset=UTF-8",null);



    }
}
