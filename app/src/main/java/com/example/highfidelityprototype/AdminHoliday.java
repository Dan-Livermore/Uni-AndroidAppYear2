package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminHoliday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_holiday);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton11);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton11);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton11);
        ImageButton navaccount = findViewById(R.id.NavAccountButton11);

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(AdminHoliday.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view ->{
            Intent intent = new Intent(AdminHoliday.this, AdminHoliday.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(AdminHoliday.this, AdminHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(AdminHoliday.this, AdminAccount.class);
            startActivity(intent);
        });

        Button IDbutton = findViewById(R.id.buttongetid);
        Button GetByID = findViewById(R.id.buttongetbyid);
        Button GetByName = findViewById(R.id.buttongetbyname);

        IDbutton.setOnClickListener(view ->{

        });

        GetByID.setOnClickListener(view ->{

        });

        GetByName.setOnClickListener(view ->{

        });

    }
}