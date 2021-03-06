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
    //TextView goalsList = (TextView) getActivity().findViewById(R.id.goalsList);

    public Goals() {
    }

    public static ArrayList<Goal> goals = new ArrayList<Goal>();
    public ArrayList<Boolean> accomplishments = new ArrayList<Boolean>();

    Activity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
       // return inflater.inflate(R.layout.fragment_goals, container, false);
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        view.findViewById(R.id.button).setOnClickListener(mListener);
        view.findViewById(R.id.allGoals).setOnClickListener(mListener);
return view;
    }
   // Button button1 = view.findViewById(R.id.button);
   // Button button2 = view.findViewById(R.id.allGoals);
   /*// public static List<Goals> getList() {
        return goals;
    }*/
   private final View.OnClickListener mListener = new View.OnClickListener() {
       public void onClick(View view) {
           switch (view.getId()) {
               case R.id.button:
                   Intent intent = new Intent(context, AddGoal.class);
                   //add data to the Intent object
                   // intent.putExtra("text", "hiii");
                   //start the second activity
                   startActivity(intent);
                   break;
               case R.id.allGoals:
                   Intent intent2 = new Intent(context, AllGoals.class);
                   //add data to the Intent object
                   // intent.putExtra("text", "hiii");
                   //start the second activity
                   startActivity(intent2);
                   break;
           }
       }
   };
   /* public void onStart() {
        super.onStart();
        Button bt = (Button) context.findViewById(R.id.button);
        Button bt2 = (Button) context.findViewById(R.id.allGoals);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.button:
                        //create an Intent object
                        Intent intent = new Intent(context, AddGoal.class);
                        //add data to the Intent object
                        // intent.putExtra("text", "hiii");
                        //start the second activity
                        startActivity(intent);
                        break;
                    case R.id.allGoals:
                        //create an Intent object
                        Intent intent2 = new Intent(context, AllGoals.class);
                        //add data to the Intent object
                        // intent.putExtra("text", "hiii");
                        //start the second activity
                        startActivity(intent2);
                        break;
                }

            }

        });
    }*/


  /*  public void onClick() {
        super.onStart();
        Button bt = (Button) context.findViewById(R.id.allGoals);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //create an Intent object
                Intent intent = new Intent(context, AllGoals.class);
                //add data to the Intent object
                // intent.putExtra("text", "hiii");
                //start the second activity
                startActivity(intent);
            }

        });
    }*/

    public void addNewGoal(View v) {
        goals.add(new Goal("study for test", 10, 10));
        Intent i = new Intent(getActivity(), AddGoal.class);
        //  i.putExtra("goals arrayList", goals);
        startActivity(i);
    }

   /* public void goToAllGoals(View v) {
        Intent i = new Intent(getActivity(), AllGoals.class);
        startActivity(i);
    }*/

  /*  public void finishedGoal() {
        for (int i = 0; i < goals.size(); i++) {
            System.out.println(i + "# Is it finished? (true/false)");
            // boolean ni = kbd.nextBoolean();
            accomplishments.add(false);
        }
    }*/
  /*  public static void createGoal(String currentName, String currentTime, String currentImportance) {
        int time = Integer.parseInt(currentTime);
        int importance = Integer.parseInt(currentImportance);
        goals.add(new Goals(importance, time, currentName));
    }*/
   /* public void displayList(View v){
        TextView goalsList = (TextView) getActivity().findViewById(R.id.goalsList);
        for(int i = 0; i < goals.size(); i++){
            String currentGoal = goals.get(i).getWord();
            String goalss = goalsList +"\n"+ currentGoal;
            goalsList.setText(goalss);
        }
    }*/

}