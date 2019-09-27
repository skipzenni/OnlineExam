package com.example.loginapi;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class TakePicture extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST =234, PICK_IMAGE_CAPTURE=0;
    private Button buttonChoose, buttonTake, buttonUpload;
    private ImageView imageView;

    private Uri filepath, uri;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        mStorage = FirebaseStorage.getInstance().getReference();

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonTake = (Button) findViewById(R.id.buttonTake  );
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonTake.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }



    private void showTakeFIle() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_IMAGE_CAPTURE);
    }

    private void showUploadFIle() {



        if(filepath != null){

            StorageReference riversRef = mStorage.child("images/profile.jpg");
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();

            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(TakePicture.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage(((int) progress)+ "% Uploading...");
                }
            })
            ;
        } else if(uri != null){

            StorageReference linkImage = mStorage.child("images/profile.jpg").child(uri.getLastPathSegment());
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();
            linkImage.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(TakePicture.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress)+ "% Uploading...");
                        }
                    });
        }

    }

    private void showFileChoosen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
        }else if(requestCode==PICK_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == buttonChoose){

            showFileChoosen();
        }else if(v == buttonUpload) {

            showUploadFIle();
        }
        else if(v == buttonTake){

            showTakeFIle();
        }
    }

}
