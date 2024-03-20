package ru.mirea.afanaseviv.activitylifecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String TAG;
    private TextView ot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TAG = "my app";
        ot = findViewById(R.id.ot);

        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart(){
        super.onStart();
        ot.setText("onStart");
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ot.setText("onRestoreInstanceState");
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ot.setText("onRestart");
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ot.setText("onResume");
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ot.setText("onPause");
        Log.i(TAG, "onPause");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ot.setText("onSaveInstanceState");
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ot.setText("onStop");
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ot.setText("onDestroy");
        Log.i(TAG, "onDestroy");
    }
}
