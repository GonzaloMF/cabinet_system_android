package com.example.csc306_project.ui.dashboard;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;

public class ArtefactDetailedActivity extends AppCompatActivity {

    private Button shareButton;
    private Button favouritesButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artefact_detailed);

        shareButton = (Button) findViewById(R.id.shareButton);
        favouritesButton = (Button) findViewById(R.id.favouritesButton);
        submitButton = (Button) findViewById(R.id.submitButton);

    }
}
