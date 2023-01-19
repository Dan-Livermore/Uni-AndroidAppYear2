package com.example.highfidelityprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //log in button switches activity
        Button mainlogin = findViewById(R.id.LogInButton2);
        Button emplogin = findViewById(R.id.empbutton2);

        // logs in
        mainlogin.setOnClickListener(view -> {
                Intent intent = new Intent(AdminLogin.this, AdminAccount.class);
                startActivity(intent);
        });

        //swaps to employee log in
        emplogin.setOnClickListener(view ->{
            Intent intent = new Intent(AdminLogin.this, MainActivity.class);
            startActivity(intent);
        });

    }
}