package com.example.csc306_project.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.dashboard.ArtefactDetailActivity;
import com.example.csc306_project.ui.dashboard.ArtefactViewActivity;
import com.example.csc306_project.ui.models.Artefact;

import java.util.List;

import db.DatabaseHelper;

public class CuratorHomeActivity extends AppCompatActivity {

    private Button addArtefactButton;
    private Button viewPendingButton;
    private DatabaseHelper databaseHelper;
    private LinearLayout artefactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curator_home);

        databaseHelper = new DatabaseHelper(this);
        artefactsList = findViewById(R.id.artefacts_list);

        addArtefactButton = (Button) findViewById(R.id.addArtefactButton);
        viewPendingButton = (Button) findViewById(R.id.view_pending_button);
        String username = getIntent().getStringExtra("username");
        TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        welcomeTextView.setText("Welcome, " + username + "!");

        addArtefactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, ArtefactDetailActivity.class);
                startActivity(intent);
            }
        });
        // View pending button here

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
            View artefactView = getLayoutInflater().inflate(R.layout.artefact_item, null, false);

            TextView textView = artefactView.findViewById(R.id.artefact_title);
            textView.setText(artefact.getTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CuratorHomeActivity.this, ArtefactViewActivity.class);
                    intent.putExtra("artefact", artefact);
                    startActivity(intent);
                }
            });

            ImageView deleteButton = artefactView.findViewById(R.id.delete_artefact);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(CuratorHomeActivity.this)
                            .setTitle("Delete Artefact")
                            .setMessage("Are you sure you want to delete this artefact?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    databaseHelper.deleteArtefact((int) artefact.getId());
                                    updateArtefactsList();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });

            artefactsList.addView(artefactView);
        }
    }


}
