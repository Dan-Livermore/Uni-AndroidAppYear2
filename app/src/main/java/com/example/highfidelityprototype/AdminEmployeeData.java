package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.AsyncTask;
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

        //create buttons for navbar
        ImageButton navlogout = findViewById(R.id.NavLogOutButton9);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton9);
        ImageButton navaccount = findViewById(R.id.NavAccountButton9);

        //log out button switches activity
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeData.this, MainActivity.class);
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

        //create update button
        Button updatebtn = findViewById(R.id.updatebutton);

        updatebtn.setOnClickListener(view -> {
            class UpdateEmployeesData extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    RequestQueue queue = Volley.newRequestQueue(AdminEmployeeData.this);

                    //creates input items
                    EditText addID = findViewById(R.id.enterID2);
                    TextView addFname = findViewById(R.id.EnterFname2);
                    TextView addLname = findViewById(R.id.enterLname1);

                    //get input data
                    int newID =Integer.parseInt(addID.getText().toString());
                    String newFname = addFname.getText().toString();
                    String newLname = addLname.getText().toString();

                    //creates input JSON
                    JSONObject object = new JSONObject();
                    try {
                        object.put("id", newID);
                        object.put("surname", newLname);
                        object.put("forename", newFname);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //updates the URL to the specific user being updated
                    String ID = addID.getText().toString();
                    String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees/" + ID;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, object,
                    //PUT = update
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(AdminEmployeeData.this, "Updated Employee", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminEmployeeData.this, "incorrect ID", Toast.LENGTH_SHORT).show();

                            //could improve to handle network errors (400 / 404 / 405)
                        }
                    });
                    //Adds the new HTTP request to queue
                    queue.add(jsonObjectRequest);
                    //If notifications have bee turned on correctly, push to the user that the data has been changed
                    if (Notifications.devicenotifications == Boolean.TRUE && Notifications.afterupdate == Boolean.TRUE) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminEmployeeData.this, "Notification 6");
                        builder.setContentTitle("Employee Update");
                        builder.setContentText("Employee Updated");
                        builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
                        builder.setAutoCancel(true);

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminEmployeeData.this);
                        managerCompat.notify(1, builder.build());
                    }
                        return null;
                    }
                }
                //calls worker thread to complete the async HTTP request
            new UpdateEmployeesData().execute();
            });


        //create button to delete a user
        Button deletebtn = findViewById(R.id.deletebutton);

        deletebtn.setOnClickListener(view -> {
            class DeleteEmployeesData extends AsyncTask<Void, Void, Void> {


                @Override
                protected Void doInBackground(Void... voids) {
                    //formats ID for use in URL
                    EditText addID = findViewById(R.id.enterID2);
                    String newID = addID.getText().toString();

                    //starts queue for JSON requests
                    RequestQueue queue = Volley.newRequestQueue(AdminEmployeeData.this);
                    //changes URL to include ID for data being update
                    String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees/" + newID;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(AdminEmployeeData.this, "User Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    Toast.makeText(AdminEmployeeData.this, "ID not found", Toast.LENGTH_SHORT).show();

                                    //could improve to handle network errors (400 / 404 / 405)
                                }
                            });
                    //adds request to queue
                    queue.add(jsonObjectRequest);
                    Intent intent = new Intent(AdminEmployeeData.this, AdminEmployeeList.class);
                    startActivity(intent);
                    //if notifications are required, one is displayed
                    if (Notifications.devicenotifications == Boolean.TRUE && Notifications.afterupdate == Boolean.TRUE) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminEmployeeData.this, "Notification 5");
                        builder.setContentTitle("Employee Update");
                        builder.setContentText("Employee Deleted");
                        builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
                        builder.setAutoCancel(true);

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminEmployeeData.this);
                        managerCompat.notify(1, builder.build());
                    }
                    return null;
                }
            }
            //calls worker thread to complete the async HTTP request
        new DeleteEmployeesData().execute();
    });


        //create holiday buttons
        Button denybtn = findViewById(R.id.DenyButton);
        Button approvebtn = findViewById(R.id.ApproveButton);

        //if notifications are turned on, display to the user that the holiday has been confirmed or denied
        denybtn.setOnClickListener(view -> {
            if (Notifications.devicenotifications = Boolean.TRUE && Notifications.isClaimed == Boolean.TRUE) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminEmployeeData.this, "Notification 3");
                builder.setContentTitle("Holiday Claim");
                builder.setContentText("Holiday Claim Denied");
                builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminEmployeeData.this);
                managerCompat.notify(3, builder.build());
            }
        });

        approvebtn.setOnClickListener(view -> {
            if (Notifications.devicenotifications = Boolean.TRUE && Notifications.isClaimed == Boolean.TRUE) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminEmployeeData.this, "Notification 4");
                builder.setContentTitle("Holiday Claim");
                builder.setContentText("Holiday Claim Approved");
                builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminEmployeeData.this);
                managerCompat.notify(4, builder.build());
            }
        });


    }
}


