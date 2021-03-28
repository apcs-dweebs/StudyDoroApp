package com.example.tabbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Accomplishments extends Fragment {
    public Accomplishments() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_accomplishments, container, false);

    }

   /* public void incrementProgress(int hi) {
        progressBar.incrementProgressBy(hi);
    }

    public void onClick(View v){
        incrementProgress(5);
    }*/
}
