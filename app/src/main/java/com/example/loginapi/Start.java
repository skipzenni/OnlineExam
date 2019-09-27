package com.example.loginapi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginapi.Common.Common;
import com.example.loginapi.Model.Question;
import com.example.loginapi.Model.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Collections;
import static com.example.loginapi.Common.Common.categoriId;

public class Start extends AppCompatActivity {

    Button btnPlay;
    FirebaseDatabase database;
    DatabaseReference Questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        database = FirebaseDatabase.getInstance();
        Questions = database.getReference("Questions");
        loadQuestion(categoriId);
    }

    private void loadQuestion(String categoriId) {
        //Clean of old question
        if(Common.questionList.size()>0)
            Common.questionList.clear();

        Questions.orderByChild("CategoriId").equalTo(categoriId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            Questions ques = postSnapshot.getValue(Questions.class);
                            Common.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        Collections.shuffle(Common.questionList);

        btnPlay = (Button) findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, Exams.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
