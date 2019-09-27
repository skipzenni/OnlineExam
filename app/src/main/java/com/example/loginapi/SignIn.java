package com.example.loginapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.loginapi.Common.Common;
import com.example.loginapi.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText editPhone, editPassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editPhone = (MaterialEditText) findViewById(R.id.editPhone);
        editPassword = (MaterialEditText) findViewById(R.id.editpassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user =database.getReference("Users");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Wait....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(editPhone.getText().toString()).exists()){
                        mDialog.dismiss();
                        Users users = dataSnapshot.child(editPhone.getText().toString()).getValue(Users.class);
                        if (users.getPass().equals(editPassword.getText().toString())){
                            Toast.makeText(SignIn.this, "Sign In Success", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(SignIn.this, TakePicture.class);
                            Common.CurrentUser = users;
                            startActivity(homeIntent);
                            finish();
                        }
                        else {
                            Toast.makeText(SignIn.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                            Toast.makeText(SignIn.this, "Users not exits in Database", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
