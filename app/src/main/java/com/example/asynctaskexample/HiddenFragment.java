package com.example.asynctaskexample;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by usuario on 17/01/18.
 */

public class HiddenFragment extends Fragment {

    public static interface  TalskCallBacks{
        void onPreExecute();
        void onProgressUptate(int i);
        void onCancelled();
        void onPostExecute();
    }

    private TalskCallBacks listener;
    private final static int MAX_LENGTH = 2000;
    private Integer[] numbers = new Integer[MAX_LENGTH];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Random rnd = new Random();
        for (int i = 0; i < MAX_LENGTH; i++) {
            numbers[i] = rnd.nextInt();
        }


        ProgressBarTask progressBarTask = new ProgressBarTask();
        progressBarTask.execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public class ProgressBarTask extends AsyncTask<Void,Integer,Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
