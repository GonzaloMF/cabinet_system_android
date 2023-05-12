package com.example.csc306_project.ui.home;

import android.os.Bundle;
// import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;

public class CuratorHomeActivity extends AppCompatActivity {

    private Button addArtefactButton;
    private Button addArtefactInfoButton;
    private Button viewPendingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curator_home);

        addArtefactButton = (Button) findViewById(R.id.addArtefactButton);
        viewPendingButton = (Button) findViewById(R.id.view_pending_button);
        String username = getIntent().getStringExtra("username");
        TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        welcomeTextView.setText("Welcome, " + username + "!");
        /*
        addArtefactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, AddArtefactActivity.class);
                startActivity(intent);
            }
        });

        viewPendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, ViewPendingActivity.class);
                startActivity(intent);
            }
        });

        */
    }
}
