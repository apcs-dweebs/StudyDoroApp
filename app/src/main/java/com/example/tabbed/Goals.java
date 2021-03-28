package com.example.tabbed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Goals extends Fragment {
    int importance;
    int time;
    String word;
    //TextView goalsList = (TextView) getActivity().findViewById(R.id.goalsList);

    public Goals() {
        this(0, 1, "none");
    }

    public Goals(int imp, int tim, String theWord) {
        importance = imp;
        time = tim;
        word = theWord;
    }

    public static ArrayList<Goals> goals = new ArrayList<Goals>();
    public ArrayList<Boolean> ture = new ArrayList<Boolean>();

    Activity context;

    //    @Nullable
    // @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.fragment_goals, container, false);

    }

   /*// public static List<Goals> getList() {
        return goals;
    }*/

    public void onStart() {
        super.onStart();
        Button bt = (Button) context.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //create an Intent object
                Intent intent = new Intent(context, AddGoal.class);
                //add data to the Intent object
                intent.putExtra("text", "hiii");
                //start the second activity
                startActivity(intent);
            }

        });
    }

    public void addNewGoal(View v) {
        goals.add(new Goals(10, 10, "study for test"));
        Intent i = new Intent(getActivity(), AddGoal.class);
        //  i.putExtra("goals arrayList", goals);
        startActivity(i);
    }
    public void goToAllGoals(View v) {
        Intent i = new Intent(getActivity(), AllGoals.class);
        startActivity(i);
    }

    public void finishedGoal(){
        for (int i = 0; i < goals.size(); i++)
        {
            System.out.println(i + "# Is it finished? (true/false)");
           // boolean ni = kbd.nextBoolean();
            ture.add(false);
        }
    }
  /*  public static void createGoal(String currentName, String currentTime, String currentImportance) {
        int time = Integer.parseInt(currentTime);
        int importance = Integer.parseInt(currentImportance);
        goals.add(new Goals(importance, time, currentName));
    }
   /* public void displayList(View v){
        for(int i = 0; i < goals.size(); i++){
            String currentGoal = goals.get(i).word;
            goalsList.setText(Integer.parseInt(currentGoal));
        }
    }*/

}