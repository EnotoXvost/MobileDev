package ru.mirea.afanaseviv.faforitebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private EditText editTextBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextBook = findViewById(R.id.editTextBook);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            TextView ageView = findViewById(R.id.textViewFavoriteBook);
            String university = extras.getString(MainActivity.KEY);
            ageView.setText("Любимая книга: " + university);
        }
    }

    public void sendUserMessage(View view) {
        String userMessage = editTextBook.getText().toString();
        Intent data = new Intent();
        data.putExtra(MainActivity.USER_MESSAGE, userMessage);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}