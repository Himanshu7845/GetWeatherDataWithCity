package com.liveline.assignmentweather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.liveline.assignmentweather.R;
import com.liveline.assignmentweather.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {


    ActivitySplashBinding binding;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash);
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
             Intent i=new Intent(Splash.this,MainActivity.class);
             startActivity(i);
             finish();

            }
        }, 3000);
    }
}
