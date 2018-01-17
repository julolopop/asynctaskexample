package com.example.asynctaskexample;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txv_message;
    private final static int MAX_LENGTH = 2000;
    private Integer[] numbers = new Integer[MAX_LENGTH];
    private Button btn_cancel;
    private Button btn_short;
    SimpleAsyncTask simpleAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv_message = (TextView)findViewById(R.id.txv_message);
        btn_short = (Button)findViewById(R.id.btn_Sort);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);



        generarNumbers();
    }



    private void generarNumbers(){
        Random rnd = new Random();
        for (int i = 0; i < MAX_LENGTH; i++) {
            numbers[i] = rnd.nextInt();
        }
    }

    /**
     * metodo que se ejecuta cuando se da a ordenar
     * @param v
     */

    public void onClickSort(View v){
        if(v == btn_short) {
            /**
             * Opción 1
             */
            //bubbleShort(numbers);
            //txv_message.setText("Operación Terminada");
            /**
             * Opción 2
             */
            //execWithThread();
            /**
             * opcion 3
             */
            simpleAsyncTask = new SimpleAsyncTask();
            simpleAsyncTask.execute();
        }

        if(v == btn_cancel){
            simpleAsyncTask.cancel(true);
        }
    }

    private void execWithThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bubbleShort();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txv_message.setText("Operación Terminada");
                    }
                });

            }
        }).start();
    }

    /**
     * Metodo que ordena un array mediante el metodo de la burbuja simple
     */
    private void bubbleShort() {
        int tmp;
        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = i+1; j < numbers.length-1; j++) {
                if(numbers[i]>numbers[j]){
                    tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j]=tmp;
                }
            }
        }
    }

    private class SimpleAsyncTask extends AsyncTask<Void,Integer,Void>{


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
            txv_message.setText(String.valueOf(s)+"%");
        }

        @Override
        protected void onPreExecute() {

            btn_cancel.setVisibility(View.VISIBLE);
            btn_short.setEnabled(false);
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            btn_cancel.setVisibility(View.INVISIBLE);
            btn_short.setEnabled(true);
            txv_message.setText("Operacion Terminada");
        }


        @Override
        protected void onCancelled() {

            btn_cancel.setVisibility(View.INVISIBLE);
            btn_short.setEnabled(true);
            txv_message.setText("Operacion Cancelada");
            super.onCancelled();

        }


    }
}
