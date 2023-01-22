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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminEmployeeData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee_data);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton9);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton9);
        ImageButton navaccount = findViewById(R.id.NavAccountButton9);

        //log out button switches activity
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeData.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeData.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeData.this, AdminAccount.class);
            startActivity(intent);
        });

        //create holiday buttons
        Button updatebtn = findViewById(R.id.updatebutton);
        Button deletebtn = findViewById(R.id.deletebutton);


        updatebtn.setOnClickListener(view -> {
            RequestQueue queue = Volley.newRequestQueue(AdminEmployeeData.this);

            EditText addFname = findViewById(R.id.EnterFname2);
            EditText addLname = findViewById(R.id.enterLname1);
            int ID = 1;
            String newFname = addFname.getText().toString();
            String newLname = addLname.getText().toString();
            JSONObject object2 = new JSONObject();
            try{
                object2.put("id", ID);
                object2.put("forename", newFname);
                object2.put("surname", newLname);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees/"+ID;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, object2,

                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(AdminEmployeeData.this, "Updated", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    Toast.makeText(AdminEmployeeData.this, "ID invalid", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);
        });




        deletebtn.setOnClickListener(view -> {
            ///ask for input
            TextView addFname = findViewById(R.id.EnterFname2);
            TextView addLname = findViewById(R.id.enterLname1);
            int ID = 1;
            String newFname = addFname.getText().toString();
            String newLname = addLname.getText().toString();
////////////////use names to find id

            RequestQueue queue = Volley.newRequestQueue(AdminEmployeeData.this);
            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees/" + ID;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error




                            //404 doesnt exist





                        }
                    });
            queue.add(jsonObjectRequest);
        });











        //create holiday buttons
        Button approvebtn = findViewById(R.id.ApproveButton);
        Button denybtn = findViewById(R.id.DenyButton);

        //approve button sends notification
        approvebtn.setOnClickListener(this::onClick);
        denybtn.setOnClickListener(this::onClick2);

        //Check device android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification 2", "Notification 2", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //Check device android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification 3", "Notification 3", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
}

    private void onClick2(View view) {
        if (Notifications.devicenotifications = Boolean.TRUE && Notifications.isClaimed == Boolean.TRUE) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminEmployeeData.this, "Notification 3");
            builder.setContentTitle("Holiday Claim");
            builder.setContentText("Holiday Claim Denied");
            builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminEmployeeData.this);
            managerCompat.notify(1, builder.build());
        }
    }

    private void onClick(View view) {
        if (Notifications.devicenotifications = Boolean.TRUE && Notifications.isClaimed == Boolean.TRUE) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminEmployeeData.this, "Notification 2");
            builder.setContentTitle("Holiday Claim");
            builder.setContentText("Holiday Claim Accepted");
            builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminEmployeeData.this);
            managerCompat.notify(1, builder.build());
        }
    }



    }