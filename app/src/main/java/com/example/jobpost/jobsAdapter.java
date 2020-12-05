package com.example.jobpost;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class jobsAdapter extends RecyclerView.Adapter<jobsAdapter.jobViewHolder> {

    private Context mCtx;
    private List<job> jobList;

    public jobsAdapter(Context mCtx, List<job> jobList) {
        this.mCtx = mCtx;
        this.jobList = jobList;
    }


    @Override
    public jobViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new jobViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_job, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NotNull jobViewHolder holder, int position) {
        job job = jobList.get(position);

        holder.textViewjob.setText(job.getJobtitle());
        holder.textViewexp.setText(job.getExperience());
        holder.textViewpay.setText(job.getPay());

    }

    @Override
    public int getItemCount() {
        return jobList.size();


    }

    class jobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewjob, textViewexp, textViewpay;

        public jobViewHolder(View itemView) {
            super(itemView);

            textViewjob = itemView.findViewById(R.id.jobname);
            textViewexp = itemView.findViewById(R.id.exp);
            textViewpay = itemView.findViewById(R.id.pay);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            job job = jobList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, Post.class);
            intent.putExtra("job profiles", job);
            mCtx.startActivity(intent);
        }
    }
}