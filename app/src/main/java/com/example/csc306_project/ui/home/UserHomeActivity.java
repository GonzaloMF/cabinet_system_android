package com.example.csc306_project.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;

public class UserHomeActivity extends AppCompatActivity {

    private Button addArtefactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        addArtefactButton = (Button) findViewById(R.id.addArtefactButton);

        String username = getIntent().getStringExtra("username");
        TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        welcomeTextView.setText("Welcome, " + username + "!");

        //addArtefactInfoButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        //TODO: Implement adding artefact info
        //    }
        //});
    }
}
