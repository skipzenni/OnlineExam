package com.example.loginapi;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.loginapi.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Exams extends AppCompatActivity implements View.OnClickListener {

    final static Long INTERVAL = Long.valueOf(1000);
    final static Long TIMOUT = Long.valueOf(7000);
    int progressValue=0;

    CountDownTimer mCountDown;

    int index=0, score=0, thisQuestion=0, totalQuestion, CurrentAnswer;

    FirebaseDatabase database;
    DatabaseReference Questions;

    ProgressBar progressBar;
    ImageView question_image;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestionNum, Question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        database = FirebaseDatabase.getInstance();
        Questions = database.getReference("Questions");

        txtScore = (TextView) findViewById(R.id.txtScore);
        txtQuestionNum = (TextView) findViewById(R.id.textTotalQuestion);
        Question = (TextView) findViewById(R.id.questionText);
        question_image = (ImageView) findViewById(R.id.questionImage);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnA = (Button) findViewById(R.id.answerA);
        btnB = (Button) findViewById(R.id.answerB);
        btnC = (Button) findViewById(R.id.answerC);
        btnD = (Button) findViewById(R.id.answerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        if(index<totalQuestion){
            Button clickButton = (Button)v;
            if(clickButton.getText().equals(Common.questionList.get(index).getCurrentAnswer())){
                score+=10;
                CurrentAnswer++;
                showQuestion(++index);
            }
            else{
                Intent intent =  new Intent(this,Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putInt("Answer", CurrentAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d", score));
        }
    }

    private void showQuestion(int index) {
        if(index<totalQuestion){
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d", thisQuestion, totalQuestion));
            progressBar.setProgress(0);
            progressValue=0;

            if(Common.questionList.get(index).getImageQuestion().equals("true")){
                //if is image wuestion
                Picasso.with(getBaseContext())
                        .load(Common.questionList.get(index).getQuestion())
                        .into(question_image);
                question_image.setVisibility(View.VISIBLE);
                Question.setVisibility(View.VISIBLE);
            }
            else {
                Question.setText(Common.questionList.get(index).getQuestion());
                question_image.setVisibility(View.INVISIBLE);
                Question.setVisibility(View.VISIBLE);
            }
            btnA.setText(Common.questionList.get(index).getAnswer_A());
            btnB.setText(Common.questionList.get(index).getAnswer_B());
            btnC.setText(Common.questionList.get(index).getAnswer_C());
            btnD.setText(Common.questionList.get(index).getAnswer_C());

            mCountDown.start();//start timer
        }
        else {
            //if it is final question
            Intent intent =  new Intent(this,Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("Answer", CurrentAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    //press ctrl + o

    @Override
    protected void onPostResume() {
        super.onPostResume();
        totalQuestion = Common.questionList.size();

        mCountDown = new CountDownTimer(TIMOUT,INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}
