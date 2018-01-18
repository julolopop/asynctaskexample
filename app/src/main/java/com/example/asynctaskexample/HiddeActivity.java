package com.example.asynctaskexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by usuario on 17/01/18.
 */

public class HiddeActivity extends AppCompatActivity implements HiddenFragment.TalskCallBacks {


    private TextView txv_message;
    private Button btn_cancel;
    private Button btn_short;
    private ProgressBar progressBar;
    HiddenFragment hiddenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv_message = (TextView) findViewById(R.id.txv_message);
        btn_short = (Button) findViewById(R.id.btn_Sort);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        hiddenFragment = (HiddenFragment) getSupportFragmentManager().findFragmentByTag("Hidden");
        if (hiddenFragment == null) {
            hiddenFragment = new HiddenFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(hiddenFragment, "Hidden");
            fragmentTransaction.commit();
        }

        if (hiddenFragment.getProgressBarTask() != null)
            if (hiddenFragment.getProgressBarTask().getStatus() == AsyncTask.Status.RUNNING) {
                btn_cancel.setVisibility(View.VISIBLE);
            }
    }


    public void onClickSort(View v) {
        if (v == btn_short) {
            hiddenFragment.iniciar();
        }

        if (v == btn_cancel) {
            hiddenFragment.cancelar();
        }
    }


    @Override
    public void onPreExecute() {
        btn_cancel.setVisibility(View.VISIBLE);
        btn_short.setEnabled(false);
    }

    @Override
    public void onProgressUptate(float i) {
        txv_message.setText(String.valueOf(i) + "%");
        progressBar.setProgress((int) i);
    }

    @Override
    public void onCancelled() {

        btn_cancel.setVisibility(View.INVISIBLE);
        btn_short.setEnabled(true);
        txv_message.setText("Operacion Cancelada");
    }

    @Override
    public void onPostExecute() {
        btn_cancel.setVisibility(View.INVISIBLE);
        btn_short.setEnabled(true);
        txv_message.setText("Operacion Terminada");

    }
}
