package com.example.asynctaskexample;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by usuario on 17/01/18.
 */

public class HiddeActivity extends AppCompatActivity  implements HiddenFragment.TalskCallBacks{


    private TextView txv_message;
    private Button btn_cancel;
    private Button btn_short;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv_message = (TextView)findViewById(R.id.txv_message);
        btn_short = (Button)findViewById(R.id.btn_Sort);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        HiddenFragment hiddenFragment = new HiddenFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(hiddenFragment, "Hidden");
        fragmentTransaction.commit();

    }



    public void onClickSort(View v){
        if(v == btn_short) {
        }

        if(v == btn_cancel){
        }
    }


    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgressUptate(int i) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute() {

    }
}
