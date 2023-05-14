package com.example.csc306_project.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.models.Artefact;

public class ArtefactViewActivity extends AppCompatActivity {

    private TextView artefactTitle;
    private TextView artefactDescription;
    private TextView artefactHistory;
    private ImageView artefactImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artefact_view);

        artefactTitle = findViewById(R.id.artefactTitle);
        artefactDescription = findViewById(R.id.artefactDescription);
        artefactHistory = findViewById(R.id.artefactHistory);
        artefactImage = findViewById(R.id.artefactImage);

        Artefact artefact = (Artefact) getIntent().getSerializableExtra("artefact");

        if (artefact != null) {
            artefactTitle.setText(artefact.getTitle());
            artefactDescription.setText(artefact.getDescription());
            artefactHistory.setText(artefact.getHistory());

            String imagePath = artefact.getImagePath();
            if (imagePath != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                artefactImage.setImageBitmap(bitmap);
                if (bitmap != null) {
                    artefactImage.setImageBitmap(bitmap);
                } else {
                    Log.d("ArtefactViewActivity", "Failed to decode image from path: " + imagePath);
                }
            }
        }
    }
}
