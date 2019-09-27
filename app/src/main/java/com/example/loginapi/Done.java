package com.example.loginapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.loginapi.Common.Common;
import com.example.loginapi.Model.Question_Score;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Done extends AppCompatActivity {
    Button btnLogout;
    TextView txtResultScore, txtResultQuestion;
    ProgressBar progressBar;

    FirebaseDatabase database;
    DatabaseReference question_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done2);
        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");

        btnLogout = (Button) findViewById(R.id.btnLogOut);
        txtResultScore = (TextView) findViewById(R.id.txtScore);
        txtResultQuestion = (TextView) findViewById(R.id.txtQuestion);
        progressBar = (ProgressBar) findViewById(R.id.DoneProgress);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Done.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        Bundle extra =  getIntent().getExtras();
        if(extra != null){
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int CurrentAnswer = extra.getInt("Answer");

            txtResultScore.setText(String.format("SCORE  :  %d", score));
            txtResultQuestion.setText(String.format("SOAL  :  %d / %d", CurrentAnswer, totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(CurrentAnswer);

            //Uploud point to DB
            question_score.child(String.format("%s_%s", Common.CurrentUser.getName(), Common.categoriId))
                    .setValue(new Question_Score(String.format("%s_%s", Common.CurrentUser.getName(), Common.categoriId),
                            Common.CurrentUser.getName(),
                            String.valueOf(score)));
        }
    }
}
