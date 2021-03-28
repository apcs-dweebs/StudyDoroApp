package com.example.tabbed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Goals extends Fragment {
    /*public Goals() {
    }*/
    //deleted constructors, getter and setter methods
//Button addGoal = ((Button)View.findViewById(R.id.addGoal));
Activity context;
//    @Nullable
   // @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.fragment_goals, container, false);
    }
    public void onStart(){
        super.onStart();
        Button bt=(Button)context.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //create an Intent object
                Intent intent=new Intent(context, AddGoal.class);
                //add data to the Intent object
                intent.putExtra("text", "hiii");
                //start the second activity
                startActivity(intent);
            }

        });
    }
  /*  public void addNewGoal(View v) {
        Intent i = new Intent(getActivity(), AddGoal.class);
        //v.getContext()
        startActivity(i);
    }*/
}