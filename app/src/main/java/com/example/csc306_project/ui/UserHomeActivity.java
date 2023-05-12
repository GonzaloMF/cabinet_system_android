package com.example.csc306_project.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc306_project.R;

public class UserHomeActivity extends AppCompatActivity {

    private Button addArtefactInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        //addArtefactInfoButton = (Button) findViewById(R.id.add_artefact_button);
        //addArtefactInfoButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        //TODO: Implement adding artefact info
        //    }
        //});
    }
}
