package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button savebutton = findViewById(R.id.SaveNotify);
        //save data button switches activity
        savebutton.setOnClickListener(view ->{
            Boolean x = Boolean.FALSE;
            Boolean y = Boolean.FALSE;
            Boolean z = Boolean.FALSE;
            if (switch1.isChecked()){
                x  = Boolean.TRUE;
            }
            if (switch2.isChecked()){
                y = Boolean.TRUE;
            }
            if (switch3.isChecked()){
                z = Boolean.TRUE;
            }
            Notifications.updateNotifications(x,y,z);
            Intent intent = new Intent(AdminNotifications.this, AdminAccount.class);
            startActivity(intent);
        });

    }
}