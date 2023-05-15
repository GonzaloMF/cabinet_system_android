package com.example.csc306_project.ui.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.dashboard.ArtefactDetailActivity;
import com.example.csc306_project.ui.models.Artefact;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import db.DatabaseHelper;

public class PendingArtefactsActivity extends AppCompatActivity {
/*
    private DatabaseHelper databaseHelper;
    private LinearLayout artefactsList;
    private Map<Long, Artefact> artefactsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_artefacts);

        databaseHelper = new DatabaseHelper(this);
        artefactsList = findViewById(R.id.pending_artefacts_list);

        updateArtefactsList();
    }

    private void updateArtefactsList() {
        List<Artefact> artefacts = databaseHelper.getPendingArtefacts();

        artefactsList.removeAllViews();

        for (Artefact artefact : artefacts) {
            artefactsMap.put(artefact.getId(), artefact);

            View artefactView = getLayoutInflater().inflate(R.layout.artefact_item, null, false);

            TextView textView = artefactView.findViewById(R.id.artefact_title);
            textView.setText(artefact.getTitle());

            // aquí necesitamos dos botones en artefact_item.xml, uno para aceptar y otro para rechazar
            Button acceptButton = artefactView.findViewById(R.id.accept_artefact);
            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    artefact.setPending(false);
                    databaseHelper.updateArtefact(artefact);
                    updateArtefactsList();
                }
            });

            Button rejectButton = artefactView.findViewById(R.id.reject_artefact);
            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseHelper.deleteArtefact((int) artefact.getId());
                    updateArtefactsList();
                }
            });

            artefactsList.addView(artefactView);
        }
    }
 */
}
