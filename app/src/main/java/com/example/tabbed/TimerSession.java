package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;

public class TimerSession extends AppCompatActivity {
    TextView countdown = findViewById(R.id.countdown);
    //int time = (findViewById(R.id.time));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_session);
        setTitle("Start New Study Session");


    }

    public static void setTimer(Number time, TextView countdown) {
        Log.d("setTimer started", "hii");
        int entered = (int) time;
        boolean x = true;
        long displayMinutes = entered - 1;
     //long starttime = System.currentTimeMillis(); //Converting to milliseconds
        while (x) {  //Infinite loop, x is always true
            int i = 59; //59 seconds, 60 is a new minute
            while (i > 0) {
                countdown.setText(displayMinutes + " Minutes and " + i + " seconds left"); //Displayed
                try {
                    i--; //Starting at 59 counting down
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                }
            }
            displayMinutes--; //Goes down when there are 0 seconds
            if ((displayMinutes <= 0) && (i == 0)) {
                x = false;
                countdown.setText("Time is up!"); //Prints after countdown is over
            }
        }
    }

    public void startStudySession(View v, Text) {
        Log.d("startStudySession", "hello");
        EditText et = (EditText)findViewById(R.id.time);
        String sTextFromET = et.getText().toString();
        int nIntFromET = new Integer(sTextFromET).intValue();
        setTimer(nIntFromET, countdown);
    }


}