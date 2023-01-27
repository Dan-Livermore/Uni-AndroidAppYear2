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

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton8);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton8);
        ImageButton navaccount = findViewById(R.id.NavAccountButton8);

        //log out button switches activity
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCreate.this, MainActivity.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCreate.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCreate.this, AdminAccount.class);
            startActivity(intent);
        });

        //Creates button to store new user in database.
        Button createbtn = findViewById(R.id.createbutton);

        //when button is pressed create the queue for the API requests
        createbtn.setOnClickListener(view -> {
            //starts worker thread
            class InsertEmployeesData extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... voids) {
                    RequestQueue queue = Volley.newRequestQueue(AdminCreate.this);
                    String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees";

                    //creates a new JSON object to store the data of the new employee in
                    JSONObject object = new JSONObject();
                    EditText addFname = findViewById(R.id.EnterFname2);
                    EditText addLname = findViewById(R.id.enterLname1);
                    EditText addID = findViewById(R.id.enterID2);

                    //gets the data from the input boxes
                    String newFname = addFname.getText().toString();
                    String newLname = addLname.getText().toString();
                    int newID = Integer.parseInt(addID.getText().toString());
                    //stores the input data into the JSON object
                    try {
                        object.put("id", newID);
                        object.put("forename", newFname);
                        object.put("surname", newLname);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            //POST = insert
                            (Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //basic error testing
                                    Toast.makeText(AdminCreate.this, "Could not create new employee, ID already used",Toast.LENGTH_LONG).show();

                                    //could improve to handle network errors (400 / 404 / 405)
                                }
                            });
                    //adds the insert to the request queue
                    queue.add(jsonObjectRequest);
                    //swaps screen back to employee list to show the user that the employee has been added
                    Intent intent = new Intent(AdminCreate.this, AdminEmployeeList.class);
                    startActivity(intent);
                    //if notifications have been correctly turned on, a push notification will be shown
                    if (Notifications.devicenotifications == Boolean.TRUE && Notifications.afterupdate == Boolean.TRUE) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminCreate.this, "Notification 4");
                        builder.setContentTitle("New Employee");
                        builder.setContentText("Employee Added");
                        builder.setSmallIcon(R.drawable.ic_baseline_adb_24);
                        builder.setAutoCancel(true);

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminCreate.this);
                        managerCompat.notify(1, builder.build());
                    }
                    return null;
                }
            }
            //calls worker thread to complete the async HTTP request
            new InsertEmployeesData().execute();
        });
    }
}