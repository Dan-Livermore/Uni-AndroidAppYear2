package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminEmployeeAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee_account);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton6);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton6);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton6);
        ImageButton navaccount = findViewById(R.id.NavAccountButton6);
        Button editdata = findViewById(R.id.TitleEditData);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(AdminEmployeeAccount.this, AdminLogin.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(AdminEmployeeAccount.this, EmployeeHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(AdminEmployeeAccount.this, AdminAccount.class);
            startActivity(intent);
        });
    }
}