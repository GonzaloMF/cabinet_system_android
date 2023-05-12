package com.example.csc306_project.ui;

import android.os.Bundle;
// import android.content.Intent;
import android.widget.Button;

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
        addArtefactInfoButton = (Button) findViewById(R.id.add_artefact_button);
        viewPendingButton = (Button) findViewById(R.id.view_pending_button);

        /*
        addArtefactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, AddArtefactActivity.class);
                startActivity(intent);
            }
        });

        addArtefactInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, AddArtefactInfoActivity.class);
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
