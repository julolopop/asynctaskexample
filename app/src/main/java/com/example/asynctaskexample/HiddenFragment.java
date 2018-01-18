package com.example.asynctaskexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Random;

/**
 * Created by usuario on 17/01/18.
 */

public class HiddenFragment extends Fragment {

    public static interface TalskCallBacks {
        void onPreExecute();

        void onProgressUptate(float i);

        void onCancelled();

        void onPostExecute();
    }

    private TalskCallBacks listener;
    private final static int MAX_LENGTH = 20000;
    private Integer[] numbers = new Integer[MAX_LENGTH];
    private ProgressBarTask progressBarTask;

    public ProgressBarTask getProgressBarTask() {
        return progressBarTask;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Random rnd = new Random();
        for (int i = 0; i < MAX_LENGTH; i++) {
            numbers[i] = rnd.nextInt();
        }




    }

    public void iniciar(){
        progressBarTask = new ProgressBarTask();
        progressBarTask.execute();
    }

    public void cancelar(){
        progressBarTask.cancel(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TalskCallBacks)
            listener = (TalskCallBacks) activity;
    }

    public class ProgressBarTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            int tmp;
            for (int i = 0; i < numbers.length-1; i++) {
                for (int j = i+1; j < numbers.length-1; j++) {
                    if(numbers[i]>numbers[j]){
                        tmp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j]=tmp;
                    }
                }
                //si no se cancela la operacion se actualiza la barra de progreso;
                if(!isCancelled()){
                    publishProgress(i , numbers.length);
                }else{
                    return  null;
                }
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            float s = ((values[0]).floatValue()/ (values[1]).floatValue())*100;
            listener.onProgressUptate(s);
        }

        @Override
        protected void onPreExecute() {

            listener.onPreExecute();
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.onPostExecute();
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            listener.onCancelled();

        }
    }


}
