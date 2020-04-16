package com.example.interview.mainactivity.Raw_Function;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.interview.R;

public class Custom_DialogBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__dialog_box);
        findViewById(R.id.insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        ViewGroup viewGroup=findViewById(android.R.id.content);

        View dialogView= LayoutInflater.from(this).inflate(R.layout.activity_custom__dialog_box,viewGroup,false);
        Button btnOK=(Button)dialogView.findViewById(R.id.okbutton);




        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setView(dialogView);

        final AlertDialog alertDialog= builder.create();


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
    }

