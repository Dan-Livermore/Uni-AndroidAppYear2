package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class AdminNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notifications);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton7);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton7);
        ImageButton navaccount = findViewById(R.id.NavAccountButton7);

        //log out button switches activity
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminNotifications.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view -> {
            Intent intent = new Intent(AdminNotifications.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminNotifications.this, AdminAccount.class);
            startActivity(intent);
        });

        // initiate switches
        Switch switch1 = (Switch) findViewById(R.id.switch4);
        Switch switch2 = (Switch) findViewById(R.id.switch3);
        Switch switch3 = (Switch) findViewById(R.id.switch2);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked()){
                    Notifications.devicenotifications  = Boolean.TRUE;
                }
                else{
                    Notifications.devicenotifications = Boolean.FALSE;
                }
            }
        });

        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch2.isChecked()){
                    Notifications.isClaimed  = Boolean.TRUE;
                }
                else{
                    Notifications.isClaimed = Boolean.FALSE;
                }
            }
        });

        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch3.isChecked()){
                    Notifications.afterupdate  = Boolean.TRUE;
                }
                else{
                    Notifications.afterupdate = Boolean.FALSE;
                }
            }
        });

    }
}