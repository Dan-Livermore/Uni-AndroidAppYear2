package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
            Intent intent = new Intent(AdminCreate.this, AdminLogin.class);
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

        Button createbtn = findViewById(R.id.createbutton);

        createbtn.setOnClickListener(view -> {
            RequestQueue queue = Volley.newRequestQueue(AdminCreate.this);
            String url ="http://web.socem.plymouth.ac.uk/COMP2000/api/employees";

            JSONObject object = new JSONObject();
            EditText addFname = findViewById(R.id.EnterFname2);
            EditText addLname = findViewById(R.id.enterLname2);
            int ID = 107;
            String newFname = addFname.getText().toString();
            String newLname = addLname.getText().toString();
            try{
                object.put("id", ID);
                object.put("forename", newFname);
                object.put("surname", newLname);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });
            queue.add(jsonObjectRequest);
            Intent intent = new Intent(AdminCreate.this, AdminEmployeeList.class);
            startActivity(intent);

        });

    }
}