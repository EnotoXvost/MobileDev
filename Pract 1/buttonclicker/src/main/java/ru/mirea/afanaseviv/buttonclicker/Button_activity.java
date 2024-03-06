package ru.mirea.afanaseviv.buttonclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Button_activity extends AppCompatActivity {

    private TextView _textViewStudent;
    private Button _btnWhoAmI;
    private Button _btnIsNotMe;
    private CheckBox _changeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_button);

        _textViewStudent = findViewById(R.id.tvOut);
        _btnWhoAmI = findViewById(R.id.btnWhoAmI);
        _btnIsNotMe = findViewById(R.id.btnItIsNotMe);
        _changeText = findViewById(R.id.ChangeTextCheckBox);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _textViewStudent.setText("Мой номер по списку №2");
                _changeText.setChecked(true);
            }
        };
        _btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }

    public void onIsNotMeClick(View view){
        _textViewStudent.setText("Это не я сделал");
        _changeText.setChecked(false);
    }
}