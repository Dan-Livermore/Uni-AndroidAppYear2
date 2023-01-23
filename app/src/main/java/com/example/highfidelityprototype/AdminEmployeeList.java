package com.example.highfidelityprototype;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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

    class CustomAdapter extends ArrayAdapter<DataModel> {
        private ArrayList<DataModel> data;

        CustomAdapter(AdminEmployeeList context, ArrayList<DataModel> data) {
            super(context, R.layout.list_view_items, data);
            this.data = data;
        }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_view_items, parent, false);

        // Get the data item for this position
        final DataModel dataModel = getItem(position);

        // Lookup view for data population
        TextView textView = customView.findViewById(R.id.text_view);
        Button button = customView.findViewById(R.id.button);

        // Populate the data into the template view using the data object
        textView.setText(dataModel.toString());
        button.setText(">");

        // Set onClickListener on the "View" button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminEmployeeData.class);
                intent.putExtra("employee_Forename", dataModel.getForename());
                intent.putExtra("employee_Surname", dataModel.getSurname());
                intent.putExtra("employee_id", dataModel.getId());
                getContext().startActivity(intent);
            }
        });


        // Return the completed view to render on screen
        return customView;
    }}

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
            Intent intent = new Intent(AdminEmployeeList.this, AdminLogin.class);
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


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees/";

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
                            CustomAdapter adapter = new CustomAdapter(AdminEmployeeList.this, data);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage()!=null)
                {
                    Log.d("AdminEmployeeList", "Error: " + error.getMessage());
                } else {
                    Log.d("Error", "null");
                }
            }
        });

        queue.add(stringRequest);
    }
}