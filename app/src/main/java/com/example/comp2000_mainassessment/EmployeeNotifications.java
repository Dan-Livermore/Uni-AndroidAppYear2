package com.example.comp2000_mainassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class EmployeeNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_notifications);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton2);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton2);
        ImageButton navaccount = findViewById(R.id.NavAccountButton2);
        Button savebutton = findViewById(R.id.SaveNotify);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeNotifications.this, MainActivity.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeNotifications.this, EmployeeHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeNotifications.this, EmployeeAccount.class);
            startActivity(intent);
        });

        //save data button switches activity
        savebutton.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeNotifications.this, EmployeeAccount.class);
            startActivity(intent);
        });

    }
}