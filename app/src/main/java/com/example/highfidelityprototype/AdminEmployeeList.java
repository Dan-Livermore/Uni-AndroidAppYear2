package com.example.highfidelityprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AdminEmployeeList extends AppCompatActivity {

    class DataModel {
        private int id;
        private String surname;
        private String forename;

        //creates template to store data in
        public DataModel(int id, String surname, String forename) {
            this.id = id;
            this.surname = surname;
            this.forename = forename;
        }

        //stores the JSON array data
        public DataModel(JSONObject jsonObject) throws JSONException {
            id = jsonObject.getInt("id");
            surname = jsonObject.getString("surname");
            forename = jsonObject.getString("forename");
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return forename + " " + surname;
        }
    }

    class CustomAdapter extends ArrayAdapter<DataModel> {
        private ArrayList<DataModel> data;

        CustomAdapter(AdminEmployeeList context, ArrayList<DataModel> data) {
            super(context, R.layout.list_view_items, data);
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //uses code in getContext() to swap screen
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //uses the additional xml file as well as the default
            View customView = inflater.inflate(R.layout.list_view_items, parent, false);

            // puts the data into correct position
            final DataModel dataModel = getItem(position);

            // creates items to store data in
            TextView textView = customView.findViewById(R.id.text_view);
            Button button = customView.findViewById(R.id.button);

            // fills items
            textView.setText(dataModel.toString());
            button.setText(">");

            //when button is pressed, swaps screens
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //formats the data to be displayed in the correct order (first name , last name)
                    Intent intent = new Intent(getContext(), AdminEmployeeData.class);
                    getContext().startActivity(intent);
                }
            });
            return customView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee_list);

        //create buttons
        ImageButton navlogout = findViewById(R.id.NavLogOutButton11);
        ImageButton navemployee = findViewById(R.id.NavEmployeeButton11);
        ImageButton navaccount = findViewById(R.id.NavAccountButton11);

        //log out button switches activity
        navlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, MainActivity.class);
            startActivity(intent);
        });

        //employee button switches activity
        navemployee.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminEmployeeList.class);
            startActivity(intent);
        });

        //account button switches activity
        navaccount.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminAccount.class);
            startActivity(intent);
        });

        FloatingActionButton addbtn = findViewById(R.id.addbutton);
        //account button switches activity
        addbtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminEmployeeList.this, AdminCreate.class);
            startActivity(intent);
        });

        //starts async HTTP queue
        class GetEmployeesData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                RequestQueue queue = Volley.newRequestQueue(AdminEmployeeList.this);
                //gets URL of webpage that contains the data
                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees/";
                //starts request for all the data
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        //GET = read
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Parse the response string and display the data in a list view
                                try {
                                    //creates a JSON array in the form as the JSON data on the API
                                    JSONArray jsonArray = new JSONArray(response);
                                    ArrayList<DataModel> data = new ArrayList<>();
                                    //iterates through all the data and adds it to the list
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        DataModel dataModel = new DataModel(jsonObject);
                                        data.add(dataModel);
                                    }
                                    //adds the list onto the list view to display the information
                                    ListView listView = findViewById(R.id.list_view);
                                    CustomAdapter adapter = new CustomAdapter(AdminEmployeeList.this, data);
                                    listView.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //basic error testing
                        Toast.makeText(AdminEmployeeList.this, "Could notfind employees",Toast.LENGTH_LONG).show();

                        //could improve to handle network errors (400 / 404 / 405)
                    }
                });
                queue.add(stringRequest);
                return null;

            }
        }
        //calls worker thread to complete the async HTTP request
        new GetEmployeesData().execute();
    }
}