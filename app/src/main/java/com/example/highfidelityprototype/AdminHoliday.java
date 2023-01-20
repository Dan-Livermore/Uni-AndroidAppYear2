package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHoliday.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHoliday.this, AdminHoliday.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHoliday.this, AdminHoliday.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHoliday.this, AdminAccount.class);
            startActivity(intent);
        });

        Button getbtn = findViewById(R.id.buttongetbyname);
        Button deletebtn = findViewById(R.id.deletebutton);
        Button putbtn = findViewById(R.id.putbutton);
        Button postbtn = findViewById(R.id.updatebutton);

        TextView textV = findViewById(R.id.textView1);

        getbtn.setOnClickListener(view -> {
            RequestQueue queue = Volley.newRequestQueue(AdminHoliday.this);
            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees/10716150";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            textV.setText("Response: " + response.toString());
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });
            queue.add(jsonObjectRequest);
        });


        deletebtn.setOnClickListener(view -> {



            ///ask for input



            RequestQueue queue = Volley.newRequestQueue(AdminHoliday.this);
            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees/10716150";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            textV.setText("Response: " + response.toString());
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


        putbtn.setOnClickListener(view -> {
            RequestQueue queue = Volley.newRequestQueue(AdminHoliday.this);
            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees";

            JSONObject object = new JSONObject();
            try{
                object.put("id", 10716150);
                object.put("forename", "Name1");
                object.put("surname", "Name2");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            textV.setText("Response: " + response.toString());
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });
            queue.add(jsonObjectRequest);
        });


        postbtn.setOnClickListener(view -> {
            RequestQueue queue = Volley.newRequestQueue(AdminHoliday.this);
            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees";

            int newID = 10716150;
            String newFName = "Name1Update";
            String newLName = "Name2Update";
            JSONObject object2 = new JSONObject();
            try{
                object2.put("id", newID);
                object2.put("forename", newFName);
                object2.put("surname", newLName);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object2,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(AdminHoliday.this, "Updated", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Toast.makeText(AdminHoliday.this, "ID invalid", Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(jsonObjectRequest);
        });



    }
}