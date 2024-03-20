package ru.mirea.afanaseviv.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

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
    }

    public void onClickShowDialog(View view){
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "alertmirea");

        Snackbar.make(view, "You click on ShowDialog button!", Snackbar.LENGTH_LONG).show();
    }

    public void onClickShowTime(View view){
        MyTimeDialogFragment timeDialog = new MyTimeDialogFragment();
        timeDialog.show(getSupportFragmentManager(), "timemirea");
    }

    public void onClickDateTime(View view){
        MyDateDialogFragment dateDialog = new MyDateDialogFragment();
        dateDialog.show(getSupportFragmentManager(), "datemirea");
    }

    public void onClickProgress(View view){
        MyProgressDialogFragment progressDialog = new MyProgressDialogFragment();
        progressDialog.show(getSupportFragmentManager(), "datemirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onTimeClicked(int hours, int minutes) {
        Toast.makeText(getApplicationContext(),
                "Время: " + hours + ":" + minutes,
                Toast.LENGTH_LONG).show();
    }

    public void onDateClicked(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getApplicationContext(),
                "Дата: " + dayOfMonth + " " + monthOfYear + " " + year,
                Toast.LENGTH_LONG).show();
    }
}