package com.example.jobpost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Save extends AppCompatActivity {

    private RecyclerView recyclerView;
    private jobsAdapter adapter;
    private List<job> jobList;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobList = new ArrayList<>();
        adapter = new jobsAdapter(this, jobList);

        recyclerView.setAdapter(adapter);


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("Jobdetails").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                          @Override
                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                              Log.v("Curyy1   ", "   " + jobList.size());


                                              progressBar.setVisibility(View.GONE);

                                              if (!queryDocumentSnapshots.isEmpty()) {

                                                  List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                                                  for (DocumentSnapshot d : list) {

                                                      job j = d.toObject(job.class);
                                                      Log.v("Curyy2   ", "   " + jobList.size());

                                                      jobList.add(j);
                                                      Log.v("Curyy3   ", "   " + jobList.size());

                                                  }

                                                  adapter.notifyDataSetChanged();

                                              }
                                          }

                                      }
                );

    }}
