package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EmployeeBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_book);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton3);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton3);
        ImageButton navaccount = findViewById(R.id.NavAccountButton3);
        Button bookbutton = findViewById(R.id.BookButton);

        //Check device android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification 1", "Notification 1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //log out button switches activity
        navlogout.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeBook.this, MainActivity.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeBook.this, EmployeeHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeBook.this, EmployeeAccount.class);
            startActivity(intent);
        });

        //save data button switches activity
        bookbutton.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        // no validation on date booked

        NotificationCompat.Builder builder = new NotificationCompat.Builder(EmployeeBook.this, "Notification 1");
        builder.setContentTitle("Holiday Claim");
        builder.setContentText("You have successfully requested holiday");
        builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(EmployeeBook.this);
        managerCompat.notify(1, builder.build());

        Intent intent = new Intent(EmployeeBook.this, EmployeeHoliday.class);
        startActivity(intent);

    }
}