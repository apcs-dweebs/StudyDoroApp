package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TimerSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_session);
        setTitle("Start New Study Session");
    }
}