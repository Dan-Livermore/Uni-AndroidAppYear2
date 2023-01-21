package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AdminAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);


        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton5);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton5);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton5);
        ImageButton navaccount = findViewById(R.id.NavAccountButton5);


        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(AdminAccount.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view ->{
            Intent intent = new Intent(AdminAccount.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(AdminAccount.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(AdminAccount.this, AdminAccount.class);
            startActivity(intent);
        });
    }
}