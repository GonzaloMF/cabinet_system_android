package com.example.csc306_project.ui.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.models.Artefact;

import java.io.File;

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
            if (imagePath != null && !imagePath.isEmpty()) {
                File imgFile = new  File(imagePath);
                if(imgFile.exists()){
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    artefactImage.setImageBitmap(bitmap);
                } else {
                    Log.d("ArtefactViewActivity", "Image file does not exist: " + imagePath);
                }
            }
        }

        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this artefact: " + artefact.getTitle());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
}
