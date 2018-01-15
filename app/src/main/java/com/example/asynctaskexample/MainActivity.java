package com.example.asynctaskexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txv_message;
    private final static int MAX_LENGTH = 20000;
    private Integer[] numbers = new Integer[MAX_LENGTH];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv_message = (TextView)findViewById(R.id.txv_message);

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
        /**
         * Opción 1
         */
        //bubbleShort(numbers);
        //txv_message.setText("Operación Terminada");
        /**
         * Opción 2
         */
        execWithThread();

    }

    private void execWithThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bubbleShort(numbers);

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
     * @param numbers
     */
    private void bubbleShort(Integer[] numbers) {
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
            return null;
        }
    }
}
