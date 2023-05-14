package com.example.csc306_project.ui.home;

import android.annotation.SuppressLint;
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

public class UserHomeActivity extends AppCompatActivity {

    private Button addArtefactButton;
    private DatabaseHelper databaseHelper;
    private LinearLayout artefactsList;
    private EditText searchBox;
    private List<Artefact> originalArtefacts = new ArrayList<>();
    private Map<Long, Artefact> artefactsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        databaseHelper = new DatabaseHelper(this);
        artefactsList = findViewById(R.id.artefacts_list);
        addArtefactButton = (Button) findViewById(R.id.addArtefactButton);
        searchBox = findViewById(R.id.searchBox);

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

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        @SuppressLint("WrongViewCast") ImageView logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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
        originalArtefacts = databaseHelper.getArtefacts();
        filter(searchBox.getText().toString());
    }

    private void filter(String text) {
        List<Artefact> filteredArtefacts = new ArrayList<>();
        for (Artefact artefact : originalArtefacts) {
            if (artefact.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredArtefacts.add(artefact);
            }
        }

        displayArtefacts(filteredArtefacts);
    }

    private void displayArtefacts(List<Artefact> artefacts) {
        artefactsList.removeAllViews();

        for (Artefact artefact : artefacts) {
            artefactsMap.put(artefact.getId(), artefact);
            View artefactView = getLayoutInflater().inflate(R.layout.artefact_item_guest, null, false);

            TextView textView = artefactView.findViewById(R.id.artefact_title);
            textView.setText(artefact.getTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserHomeActivity.this, ArtefactViewActivity.class);
                    intent.putExtra("artefact", artefactsMap.get(artefact.getId())); // pass the artefact object
                    startActivity(intent);
                }
            });

            artefactsList.addView(artefactView);
        }
    }
}

