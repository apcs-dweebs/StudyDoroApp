package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.tabbed.Goals.goals;

public class AllGoals extends AppCompatActivity {
  //  private static final String TAG = "MainActivity";
  //TextView goalsList = findViewById(R.id.goalsList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goals);
        setTitle("all goals");
       // displayList();
    }

   /* public void displayList(){
        for(int i = 0; i < goals.size(); i++){
            String currentGoal = goals.get(i).getWord();
            String goalss = goalsList +"\n"+ currentGoal;
            goalsList.setText(goalss);
        }
    }*/




    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}