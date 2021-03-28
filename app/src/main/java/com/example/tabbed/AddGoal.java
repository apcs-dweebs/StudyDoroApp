package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.tabbed.Goals.goals;


public class AddGoal extends AppCompatActivity {
    //TextView goalsList = findViewById(R.id.goalsList);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        setTitle("Add New Goals");
        Intent i = getIntent();
        //ArrayList<Goals> list = getIntent().getArrayExtra("goals arrayList");

    }
    public void addGoalToList(View v) {
        String name = findViewById(R.id.name_input).toString();
        String time = findViewById(R.id.time_input).toString();
        String importance = findViewById(R.id.importance_input).toString();
        createGoal(name, time, importance);
    }
    public void createGoal(String currentName, String currentTime, String currentImportance) {
        Toast.makeText(AddGoal.this, "Goal Added", Toast.LENGTH_SHORT).show();
        int time = Integer.parseInt(currentTime);
        int importance = Integer.parseInt(currentImportance);
        goals.add(new Goal(currentName, time, importance));

    }



/*public void displayList(View v){
        for(int i = 0; i < Goals.goals.size(); i++){
            String currentGoal = Goals.goals.get(i).word;
            updateTextView(currentGoal);
        }
}
    private void updateTextView(final String s) {
        AddGoal.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.goalsList);
                tv.setText(tv + s);
            }
        });

    }*/
}
