package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerSession extends AppCompatActivity {
    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;

//Todo: add the default value as 50 minutes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_session);
        setTitle("Start New Study Session");
        mEditTextInput = findViewById(R.id.time_header);
        mTextViewCountDown = findViewById(R.id.countdown);
        mButtonSet = findViewById(R.id.set_timer);
        mButtonStartPause = findViewById(R.id.breakCountdownBtn);
        mButtonReset = findViewById(R.id.resetBtn);
        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(TimerSession.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(TimerSession.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                mEditTextInput.setText("");
            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

    }

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }
    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
                launchBreakActivity();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }
    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
            findViewById(R.id.timePrompt).setVisibility(View.INVISIBLE);
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }

public void launchBreakActivity(){
        Intent i = new Intent(TimerSession.this, Breaktime.class);
        startActivity(i);
}

/*
    public void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private int setTime(Number minutesInput) {
        return (int) minutesInput * 6000;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        countdownButton.setText("PAUSE");
        timerRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText("START");
        timerRunning = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
    }


  /*  public void sampleTimer(View v) throws InterruptedException {
        TextView countdown = findViewById(R.id.countdown);
        for (int i = 59; i > 0; i--) {
            try {
                Thread.sleep(3000);
                updateTextView(String.valueOf(i));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.getLocalizedMessage();
            }
        }
    }


    private static class MyHandler extends Handler {
    }

    private final MyHandler mHandler = new MyHandler();

    public class MyRunnable implements Runnable {
        private final WeakReference<Activity> mActivity;

        public MyRunnable(Activity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void run() {

            Activity activity = mActivity.get();
            if (activity != null) {
                //Button btn = (Button) activity.findViewById(R.id.startStudySession);
                updateTextView("Its Working");
                onClick();
            }
        }
    }

    private MyRunnable mRunnable = new MyRunnable(this);

    public void onClick() {
        //my_button.setBackgroundResource(R.drawable.icon);
        //updateTextView("Something is happeneing");
        // Execute the Runnable in 2 seconds
        mHandler.postDelayed(mRunnable, 2000);
    }

    public void something(View v) {
        for (int i = 59; i > 0; i--) {
            updateTextView("Something is happeneing" + i);
            onClick();
        }
    }


    public void thing(View v) {
        Thread closeActivity = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    for (int i = 59; i > 0; i--) {
                        updateTextView(String.valueOf(i));
                    }
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });
    }

    private void updateTextView(final String s) {
        TimerSession.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.countdown);
                tv.setText("Text = " + s);
            }
        });

    }

    public void setTimer(View v) {
       /* EditText et = findViewById(R.id.time);
        String to_string = et.getText().toString();
        int to_int = Integer.parseInt(to_string);*
        TextView countdown = findViewById(R.id.countdown);
        int entered = 2;
        boolean x = true;
        long displayMinutes = entered - 1;
        while (x) {
            int i = 59;
            while (i > 0) {
                String countdownText = (displayMinutes + " Minutes and " + i + " seconds left");
                updateTextView(countdownText);
                try {
                    i--;
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            displayMinutes--;
            if ((displayMinutes <= 0) && (i == 0)) {
                x = false;
                countdown.setText(R.string.times_up);
            }
        }
    }*/

   /* public void setTimer(int time, TextView countdown) {
        countdown = findViewById(R.id.countdown);
        //Log.d("setTimer started", "hii");
        int entered = time;
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

    public void startStudySession(View v) {
        EditText et = findViewById(R.id.time);
        String to_string = et.getText().toString();
        int to_int = new Integer(to_string).intValue();
        int new_int = Integer.valueOf(to_string);
        setTimer(to_int, ((TextView)findViewById(R.id.countdown)));
    }*/


}