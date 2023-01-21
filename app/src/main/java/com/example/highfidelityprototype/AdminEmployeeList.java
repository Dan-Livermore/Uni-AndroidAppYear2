package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AdminEmployeeList extends AppCompatActivity {

    private RequestQueue queue;
    class DataModel {
        private int id;
        private String surname;
        private String forename;

        public DataModel(int id, String surname, String forename) {
            this.id = id;
            this.surname = surname;
            this.forename = forename;
        }

        public DataModel(JSONObject jsonObject) throws JSONException{
            id = jsonObject.getInt("id");
            surname = jsonObject.getString("surname");
            forename = jsonObject.getString("forename");
        }

        public int getId(){
            return id;
        }

        public String getSurname(){
            return surname;
        }

        public String getForename(){
            return  forename;
        }
        @Override
        public String toString() {
            return forename + " " + surname;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee_list);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton11);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton11);
        ImageButton navholiday = findViewById(R.id.NavHolidayButton11);
        ImageButton navaccount = findViewById(R.id.NavAccountButton11);

        //Button createbtn = findViewById(R.id.addbutton);

        //log out button switches activity
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminLogin.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //holiday button switches activity
        navholiday.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminAccount.class);
            startActivity(intent);
        });

        //add button switches activity
        //createbtn.setOnClickListener(view -> {
        //Intent intent = new Intent(AdminEmployeeList.this, AdminCreate.class);
        //    startActivity(intent);
        //});

        RequestQueue queue = Volley.newRequestQueue(AdminEmployeeList.this);
        String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response string and display the data in a list view
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<DataModel> data = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                DataModel dataModel = new DataModel(jsonObject);
                                data.add(dataModel);
                            }
                            ListView listView = findViewById(R.id.list_view);
                            ArrayAdapter<DataModel> adapter = new ArrayAdapter<>(AdminEmployeeList.this,
                                    android.R.layout.simple_list_item_1, data);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Log.d("AdminPage", "Error: " + error.getMessage());
                } else {
                    Log.d("Error", "Error message is null");
                }
            }

        });
    }
}