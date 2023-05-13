package com.example.csc306_project.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;

public class GuestHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);

        String username = getIntent().getStringExtra("username");
        //TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        //welcomeTextView.setText("Welcome, " + username + "!");
    }
}
