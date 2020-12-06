package com.example.jobpost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Post extends AppCompatActivity {
    private Button post,read;
    private TextView job,experience,payperhour;


    FirebaseFirestore db= FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        job=findViewById(R.id.jobname);
        experience=findViewById(R.id.exp);
        payperhour=findViewById(R.id.pay);
        post=findViewById(R.id.button2);
        read=findViewById(R.id.button3);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addjob();
            }
        });
        read.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Post.this,Save.class));
            }
        });

    }
    private boolean validate(String jobtitle,String exp,String pay){
        if(jobtitle.isEmpty()){
            job.setError("Job Title Required");
            job.requestFocus();
        }
        if(exp.isEmpty()){
            experience.setError("Experience Required");
            experience.requestFocus();
        }
        if(pay.isEmpty()){
            payperhour.setError("Pay per hour is Required");
            payperhour.requestFocus();
        }
        return false;
    }
    private void addjob( ){
        String jobtitle=job.getText().toString().trim();
        String exp=experience.getText().toString().trim() ;
        String pay=payperhour.getText().toString().trim();
        if( !validate(jobtitle,exp,pay)){

            CollectionReference dbjob=db.collection("Jobdetails");
            job job=new job(jobtitle,exp,pay);
            dbjob.add(job) .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(Post.this, "Product Added", Toast.LENGTH_LONG).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Post.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }



}