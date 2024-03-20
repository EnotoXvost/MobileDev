package ru.mirea.afanaseviv.checkstudent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFIO;
    private TextView textViewUni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewFIO = findViewById(R.id.textViewFIO);
        textViewUni = findViewById(R.id.textViewUni);

        Intent intent = getIntent();
        if (intent != null) {
            String FIO = intent.getStringExtra(Intent.EXTRA_TEXT);
            String Uni = intent.getStringExtra(Intent.EXTRA_SUBJECT);

            if (FIO != null) {
                textViewFIO.setText("ФИО: " + FIO);
            }

            if (Uni != null) {
                textViewUni.setText("Университет: " + Uni);
            }
        }
    }
}