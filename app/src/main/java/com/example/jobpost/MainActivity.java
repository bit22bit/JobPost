package com.example.jobpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=(Button)findViewById(R.id.signup);



    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.signup:startActivity(new Intent(this,Signup.class));
        }
    }

}
