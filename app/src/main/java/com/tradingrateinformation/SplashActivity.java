package com.tradingrateinformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tradingrateinformation.config.Database;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Database.userArrayList = new ArrayList<>();
        Database.GetData();

        Timer timer = new Timer();
        timer.schedule(new Process(SplashActivity.this),2000,2000);

    }
    public class Process extends TimerTask {
        Context context;
        public Process(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (Database.userArrayList.size() > 0) {
                this.cancel();
                startActivity(new Intent(SplashActivity.this, SignInScreen.class));
                finishAffinity();
            }
        }
    }
}