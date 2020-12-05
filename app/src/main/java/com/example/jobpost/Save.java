package com.example.jobpost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Save extends AppCompatActivity {

    private TextView tread;
    private Button bread;

    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        tread = findViewById(R.id.read);
        bread = findViewById(R.id.readdata);
        db = FirebaseFirestore.getInstance();
        bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });



    }
    public void getdata(){
        Log.v("one","in method") ;


        db.collection("Jobdetails")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.v("one","inclass") ;
                        if (task.isSuccessful()){
                            Log.v("Teo","inif") ;

                            String results="";
                            for (DocumentSnapshot document: task.getResult()){
                                Log.d("result", document.getId() + " => " + document.getData());
                                job job= document.toObject(com.example.jobpost.job.class);
                                results="Job Title: "+job.getJobtitle()+
                                        "\nExperience in:"+job.getExperience()+
                                        "\nPay Per hour:"+job.getPay();
                            }
                            tread.setText(results);
                        }
                    }
                })  .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("error",""+e.getMessage()
                );
            }
        });

    }
}
