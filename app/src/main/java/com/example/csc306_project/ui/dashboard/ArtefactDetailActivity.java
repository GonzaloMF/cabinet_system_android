package com.example.csc306_project.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;
import com.example.csc306_project.ui.models.Artefact;

import db.DatabaseHelper;

public class ArtefactDetailActivity extends AppCompatActivity {

    private EditText artefactTitle;
    private EditText artefactDescription;
    private EditText artefactHistory;

    private static final int PICK_IMAGE = 1;
    private ImageView artefactImage;
    private String imagePath;
    private Uri selectedImageUri;

    private long artefactId = -1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artefact_detailed);

        artefactTitle = findViewById(R.id.artefactTitle);
        artefactDescription = findViewById(R.id.artefactDescription);
        artefactHistory = findViewById(R.id.artefactHistory);

        // Initialize the ImageView and Button
        artefactImage = findViewById(R.id.artefactImage);
        Button selectImageButton = findViewById(R.id.selectImageButton);

        Button submitButton = findViewById(R.id.submitButton);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        Artefact artefact = (Artefact) getIntent().getSerializableExtra("artefact");
        if (artefact != null) {
            // We are editing an existing artefact
            artefactId = artefact.getId();
            artefactTitle.setText(artefact.getTitle());
            artefactDescription.setText(artefact.getDescription());
            artefactHistory.setText(artefact.getHistory());

            if (artefact.getImagePath() != null) {
                Uri imageUri = Uri.parse(artefact.getImagePath());
                artefactImage.setImageURI(imageUri);
            }
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = artefactTitle.getText().toString();
                String description = artefactDescription.getText().toString();
                String history = artefactHistory.getText().toString();

                String imagePath = null;
                if (selectedImageUri != null) {
                    imagePath = getPathFromUri(selectedImageUri);
                }
                DatabaseHelper databaseHelper = new DatabaseHelper(ArtefactDetailActivity.this);
                if (artefactId == -1) {
                    // We are adding a new artefact
                    Artefact newArtefact = new Artefact(-1, title, description, history, imagePath);
                    databaseHelper.addArtefact(newArtefact);
                    Toast.makeText(ArtefactDetailActivity.this, "Artefact added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // We are updating an existing artefact
                    Artefact updatedArtefact = new Artefact(artefactId, title, description, history, imagePath);
                    databaseHelper.updateArtefact(updatedArtefact);
                    Toast.makeText(ArtefactDetailActivity.this, "Artefact updated successfully!", Toast.LENGTH_SHORT).show();
                }

                finish();  // This will finish this activity and go back to the previous one
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                selectedImageUri = data.getData();
                artefactImage.setImageURI(selectedImageUri);
                imagePath = getPathFromUri(selectedImageUri);
            }
        }
    }

    public String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }

}
