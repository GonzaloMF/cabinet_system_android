package com.example.csc306_project.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.models.Artefact;

import db.DatabaseHelper;

public class ArtefactDetailActivity extends AppCompatActivity {

    private EditText artefactTitle;
    private EditText artefactDescription;
    private EditText artefactHistory;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artefact_detailed);

        artefactTitle = findViewById(R.id.artefactTitle);
        artefactDescription = findViewById(R.id.artefactDescription);
        artefactHistory = findViewById(R.id.artefactHistory);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = artefactTitle.getText().toString();
                String description = artefactDescription.getText().toString();
                String history = artefactHistory.getText().toString();

                Artefact newArtefact = new Artefact(title, description, history);
                DatabaseHelper databaseHelper = new DatabaseHelper(ArtefactDetailActivity.this);
                databaseHelper.addArtefact(newArtefact);

                Toast.makeText(ArtefactDetailActivity.this, "Artefact added successfully!", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
