package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AdminNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notifications);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton7);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton7);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton7);
        ImageButton navaccount = findViewById(R.id.NavAccountButton7);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(AdminNotifications.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view ->{
            Intent intent = new Intent(AdminNotifications.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(AdminNotifications.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(AdminNotifications.this, AdminAccount.class);
            startActivity(intent);
        });
    }
}