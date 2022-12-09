package com.example.comp2000_mainassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class EmployeeEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton1);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton1);
        ImageButton navaccount = findViewById(R.id.NavAccountButton1);
        Button notifybutton = findViewById(R.id.NotificationSettingsButton);
        Button savebutton = findViewById(R.id.SaveButton);
        Button discardbutton = findViewById(R.id.DiscardButton);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeEdit.this, MainActivity.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeEdit.this, EmployeeHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeEdit.this, EmployeeAccount.class);
            startActivity(intent);
        });

        //notifications data button switches activity
        notifybutton.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeEdit.this, EmployeeNotifications.class);
            startActivity(intent);
        });

        //save data button switches activity
        savebutton.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeEdit.this, EmployeeAccount.class);
            startActivity(intent);
        });

        //notifications data button switches activity
        discardbutton.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeEdit.this, EmployeeAccount.class);
            startActivity(intent);
        });
    }
}