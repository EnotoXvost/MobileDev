package ru.mirea.afanaseviv.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDateDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar date = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((MainActivity) getActivity()).onDateClicked(year, monthOfYear, dayOfMonth);
            }
        };
        DatePickerDialog dateDialog = new DatePickerDialog(getActivity(),
                    dateListener, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        dateDialog.setTitle("Выберите время");
        dateDialog.show();
        return dateDialog;
    }
}