package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EmployeeAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_account);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton);
        ImageButton navaccount = findViewById(R.id.NavAccountButton);
        Button editdata = findViewById(R.id.TitleEditData);
        Button notifybutton = findViewById(R.id.NotificationSettingsButton);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeAccount.this, MainActivity.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeAccount.this, EmployeeHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeAccount.this, EmployeeAccount.class);
            startActivity(intent);
        });

        //edit data button switches activity
        editdata.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeAccount.this, EmployeeEdit.class);
            startActivity(intent);
        });

        //notifications data button switches activity
        notifybutton.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeAccount.this, EmployeeNotifications.class);
            startActivity(intent);
        });
    }
}