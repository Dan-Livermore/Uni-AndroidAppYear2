package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class EmployeeHoliday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_holiday);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton4);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton4);
        ImageButton navaccount = findViewById(R.id.NavAccountButton4);
        Button bookbutton = findViewById(R.id.BookButton2);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeHoliday.this, MainActivity.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeHoliday.this, EmployeeHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeHoliday.this, EmployeeAccount.class);
            startActivity(intent);
        });

        //save data button switches activity
        bookbutton.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeHoliday.this, EmployeeBook.class);
            startActivity(intent);
        });
    }
}