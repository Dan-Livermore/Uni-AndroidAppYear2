package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //log in button switches activity
        Button mainlogin = findViewById(R.id.LogInButton2);
        Button emplogin = findViewById(R.id.empbutton2);

        // logs in
        mainlogin.setOnClickListener(view -> {
            // no validation on inputs
            EditText username = (EditText) findViewById(R.id.EmailTextbox);
            String user = username.getText().toString();
            EditText password = (EditText) findViewById(R.id.PasswordTextbox);
            String passw = password.getText().toString();
            if (user.equals("") && passw.equals("")) {
                Toast.makeText(MainActivity.this, "Enter email and password",
                        Toast.LENGTH_SHORT).show();
            } else if (user.equals("")) {
                Toast.makeText(MainActivity.this, "Enter valid email",
                        Toast.LENGTH_SHORT).show();
            } else if (passw.equals("")) {
                Toast.makeText(MainActivity.this, "Enter password",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, AdminAccount.class);
                startActivity(intent);
            }
        });

        //swaps to employee log in
        emplogin.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, EmployeeLogin.class);
            startActivity(intent);
        });

    }
}