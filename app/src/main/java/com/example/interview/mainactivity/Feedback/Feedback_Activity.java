package com.example.interview.mainactivity.Feedback;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.interview.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Feedback_Activity extends AppCompatActivity {

    TextInputEditText feedbackname;
    TextInputEditText feedbackemail;
    TextInputEditText feedbackcontent;

    FloatingActionButton sendfeedback;


    List<Feedback_Pojo>FeedbackData;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_);

        feedbackname=(TextInputEditText)findViewById(R.id.feedback_name);
        feedbackemail=(TextInputEditText)findViewById(R.id.feedback_email);
        feedbackcontent=(TextInputEditText)findViewById(R.id.feedback_content);

        sendfeedback=(FloatingActionButton)findViewById(R.id.sendfeedback);

        FeedbackData = new ArrayList<>();
        databaseArtists = FirebaseDatabase.getInstance().getReference("Interview Preparation Feedback");

sendfeedback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        addFeedbackData();
    }
});

    }

    private void addFeedbackData() {

        String name = feedbackname.getText().toString().trim();
        String email = feedbackemail.getText().toString().trim();
        String content = feedbackcontent.getText().toString().trim();


        if (!TextUtils.isEmpty(name)) {


            String Name = databaseArtists.push().getKey();

            Feedback_Pojo artist = new Feedback_Pojo(name, email, content);

            databaseArtists.child(name).setValue(artist);

            feedbackname.setText("");

            Toast.makeText(this, "Successful sent", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Can't sent the feedback", Toast.LENGTH_LONG).show();
        }

        }
    }

