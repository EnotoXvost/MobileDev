package ru.mirea.afanaseviv.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.logging.Handler;

public class MyProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Загрузка");
        progressDialog.setMessage("Ничего не загружается, но ждите!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);

        // Simulate a download task
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int progress = 0; progress <= 100; progress += 10) {
                    try {
                        Thread.sleep(1000); // Simulate download time
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
                // Perform any action after the download is completed
            }
        }).start();

        progressDialog.show();

        return progressDialog;
    }
}
