package com.example.csc306_project.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.dashboard.ArtefactDetailActivity;
import com.example.csc306_project.ui.dashboard.ArtefactViewActivity;
import com.example.csc306_project.ui.dashboard.LoginActivity;
import com.example.csc306_project.ui.models.Artefact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DatabaseHelper;

public class CuratorHomeActivity extends AppCompatActivity {

    private Button addArtefactButton;
    private Button viewPendingButton;
    private DatabaseHelper databaseHelper;
    private LinearLayout artefactsList;
    private Map<Long, Artefact> artefactsMap = new HashMap<>();
    private List<Artefact> originalArtefacts;
    private List<Artefact> filteredArtefacts;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curator_home);

        databaseHelper = new DatabaseHelper(this);
        artefactsList = findViewById(R.id.artefacts_list);
        searchBox = findViewById(R.id.searchBox);

        addArtefactButton = findViewById(R.id.addArtefactButton);
        viewPendingButton = findViewById(R.id.view_pending_button);
        String username = getIntent().getStringExtra("username");
        TextView welcomeTextView = findViewById(R.id.welcome_text_view);
        welcomeTextView.setText("Welcome, " + username + "!");

        // Add new Artefact
        addArtefactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, ArtefactDetailActivity.class);
                startActivity(intent);
            }
        });

        // Log out
        @SuppressLint("WrongViewCast") ImageView logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuratorHomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        originalArtefacts = databaseHelper.getArtefacts();
        filteredArtefacts = new ArrayList<>(originalArtefacts);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        updateArtefactsList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        originalArtefacts = databaseHelper.getArtefacts();  // update the original artefacts list
        filter(searchBox.getText().toString());  // apply the current filter
    }

    private void filter(String text) {
        filteredArtefacts.clear();
        if (text.isEmpty()) {
            filteredArtefacts.addAll(originalArtefacts);
        } else {
            text = text.toLowerCase();
            for (Artefact artefact : originalArtefacts) {
                if (artefact.getTitle().toLowerCase().contains(text)) {
                    filteredArtefacts.add(artefact);
                }
            }
        }
        updateArtefactsList();
    }

    private void updateArtefactsList() {
        List<Artefact> artefacts = filteredArtefacts;

        artefactsList.removeAllViews();

        for (Artefact artefact : artefacts) {

            // Add each artefact to the map with their ID as a key
            artefactsMap.put(artefact.getId(), artefact);

            View artefactView = getLayoutInflater().inflate(R.layout.artefact_item, null, false);

            TextView textView = artefactView.findViewById(R.id.artefact_title);
            textView.setText(artefact.getTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CuratorHomeActivity.this, ArtefactViewActivity.class);
                    intent.putExtra("artefact", artefactsMap.get(artefact.getId()));  // pass the artefact object
                    startActivity(intent);
                }
            });

            // Delete artefact created
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
                                    originalArtefacts = databaseHelper.getArtefacts();
                                    filter(searchBox.getText().toString());
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });

            // Update the artefact created
            ImageView updateButton = artefactView.findViewById(R.id.update_artefact);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CuratorHomeActivity.this, ArtefactDetailActivity.class);
                    intent.putExtra("artefact", artefactsMap.get(artefact.getId()));  // pass the artefact object
                    startActivity(intent);
                }
            });

            artefactsList.addView(artefactView);
        }
    }
}

