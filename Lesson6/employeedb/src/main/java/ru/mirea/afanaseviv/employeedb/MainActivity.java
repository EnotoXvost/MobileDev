package ru.mirea.afanaseviv.employeedb;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();


        Employee superhero = new Employee();
        superhero.name = "supwr hero";
        superhero.alias = "imagine super puper hero";
        superhero.superPower = "power one, power two";
        employeeDao.insert(superhero);

        List<Employee> superheroes = employeeDao.getAll();
        for (Employee hero : superheroes) {
            Log.d(TAG, "Superhero: " + hero.name + ", Alias: " + hero.alias + ", Powers: " + hero.superPower);
        }
    }
}