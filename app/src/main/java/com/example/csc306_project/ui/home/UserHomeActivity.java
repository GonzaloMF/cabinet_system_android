package com.example.csc306_project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.dashboard.ArtefactDetailActivity;
import com.example.csc306_project.ui.dashboard.ArtefactViewActivity;
import com.example.csc306_project.ui.models.Artefact;

import java.util.List;

import db.DatabaseHelper;

public class UserHomeActivity extends AppCompatActivity {

    private Button addArtefactButton;
    private DatabaseHelper databaseHelper;
    private LinearLayout artefactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        databaseHelper = new DatabaseHelper(this);
        artefactsList = findViewById(R.id.artefacts_list);
        addArtefactButton = (Button) findViewById(R.id.addArtefactButton);

        String username = getIntent().getStringExtra("username");
        TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        welcomeTextView.setText("Welcome, " + username + "!");

        addArtefactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, ArtefactDetailActivity.class);
                startActivity(intent);
            }
        });

        updateArtefactsList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateArtefactsList();
    }

    private void updateArtefactsList() {
        List<Artefact> artefacts = databaseHelper.getArtefacts();

        artefactsList.removeAllViews();

        for (Artefact artefact : artefacts) {
            View artefactView = getLayoutInflater().inflate(R.layout.artefact_item_guest, null, false);

            TextView textView = artefactView.findViewById(R.id.artefact_title);
            textView.setText(artefact.getTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserHomeActivity.this, ArtefactViewActivity.class);
                    intent.putExtra("artefact", artefact);
                    startActivity(intent);
                }
            });

            artefactsList.addView(artefactView);
        }
    }
}
