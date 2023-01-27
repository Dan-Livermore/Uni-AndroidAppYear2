package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        //log in button switches activity
        Button mainlogin = findViewById(R.id.LogInButton);
        Button adminlogin = findViewById(R.id.adminbutton);


        mainlogin.setOnClickListener(view -> {
            // no validation on inputs
            EditText username = (EditText) findViewById(R.id.EmailTextbox);
            String user = username.getText().toString();
            EditText password = (EditText) findViewById(R.id.PasswordTextbox);
            String passw = password.getText().toString();
            if (user.equals("") && passw.equals("")) {
                Toast.makeText(EmployeeLogin.this, "Enter email and password",
                        Toast.LENGTH_SHORT).show();
            } else if (user.equals("")) {
                Toast.makeText(EmployeeLogin.this, "Enter valid email",
                        Toast.LENGTH_SHORT).show();
            } else if (passw.equals("")) {
                Toast.makeText(EmployeeLogin.this, "Enter password",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(EmployeeLogin.this, EmployeeAccount.class);
                startActivity(intent);
            }
        });

        //swaps to employee log in
        adminlogin.setOnClickListener(view ->{
            Intent intent = new Intent(EmployeeLogin.this, MainActivity.class);
            startActivity(intent);
        });

    }
}