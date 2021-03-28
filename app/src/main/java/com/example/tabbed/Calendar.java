package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {

    EditText nameOfEvent;
    EditText description;
    Button addEvent;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setTitle("Create a calendar study event");

        nameOfEvent = findViewById(R.id.nameOfEvent);
        description = findViewById(R.id.description);
        addEvent = findViewById(R.id.addEvent);
        email = findViewById(R.id.email_input).toString();

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameOfEvent.getText().toString().isEmpty() && !description.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, nameOfEvent.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra(Intent.EXTRA_EMAIL, email);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(Calendar.this, "There is no app that can support this action", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(Calendar.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
