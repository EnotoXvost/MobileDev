package ru.mirea.afanaseviv.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar time = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                ((MainActivity)getActivity()).onTimeClicked(hourOfDay, minute);
            }
        };
        TimePickerDialog timeDialog = new TimePickerDialog(getActivity(),
                timeListener, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true);
        timeDialog.setTitle("Выберите время");
        timeDialog.show();
        return timeDialog;
    }

}
