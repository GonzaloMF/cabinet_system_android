package com.example.csc306_project.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.home.CuratorHomeActivity;
import com.example.csc306_project.ui.home.GuestHomeActivity;
import com.example.csc306_project.ui.home.UserHomeActivity;

import db.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button guestLoginButton;
    private Button createUserButton;
    private Button createCuratorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        username = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
        loginButton = (Button) findViewById(R.id.login_button);
        guestLoginButton = (Button) findViewById(R.id.guest_login_button);
        createUserButton = (Button) findViewById(R.id.create_user_button);
        createCuratorButton = (Button) findViewById(R.id.create_curator_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin(username.getText().toString(), password.getText().toString());
            }
        });

        guestLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GuestHomeActivity.class);
                startActivity(intent);
            }
        });
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(username.getText().toString(), password.getText().toString());
            }
        });
        createCuratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCurator(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void validateLogin(String user, String pass) {
        String role = databaseHelper.getUserRole(user, pass);
        if (user.isEmpty() || pass.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (role != null) {
            Intent intent = null;
            if (role.equals("curator")) {
                intent = new Intent(LoginActivity.this, CuratorHomeActivity.class);
                intent.putExtra("username", user);
            } else if (role.equals("standard")) {
                intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                intent.putExtra("username", user);
            }
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void createUser(String user, String pass) {
        if (user.isEmpty() || pass.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        databaseHelper.addUser(user, pass, "standard");
        Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
    }
    private void createCurator(String user, String pass) {
        if (user.isEmpty() || pass.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        databaseHelper.addCurator(user, pass);
        Toast.makeText(getApplicationContext(), "Curator created", Toast.LENGTH_SHORT).show();
    }
}
