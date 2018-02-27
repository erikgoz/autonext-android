package com.example.goz.autonext;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button prevButton, nextButton, playButton, pauseButton, goButton, clearButton;
    TextView outputTextView;
    EditText delayEditText;
    Worker worker; //for single use

    String skip="input keyevent 87";

    String PREV = "input keyevent 88";
    String NEXT = "input keyevent 87";
    String PLAY = "input keyevent 126";
    String PAUSE = "input keyevent 127";
    String PR = "PREV";
    String NE = "NEXT";
    String PL = "PLAY";
    String PA = "PAUSE";

    String skip2 = skip + " && " + skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.prevButton = (Button)findViewById(R.id.button_prev);
        this.nextButton = (Button)findViewById(R.id.button_next);
        this.playButton = (Button)findViewById(R.id.button_play);
        this.pauseButton = (Button)findViewById(R.id.button_pause);
        this.goButton = (Button)findViewById(R.id.button_go);
        this.clearButton = (Button)findViewById(R.id.button_clear);
        this.outputTextView = (TextView)findViewById(R.id.textView_output);
        this.outputTextView.setText("Output\n");
        //this.delayEditText = (EditText)findViewById(R.id.editText_delay);

        this.prevButton.setOnClickListener(v -> clickPrev(v));
        this.nextButton.setOnClickListener(v -> clickNext(v));
        this.playButton.setOnClickListener(v -> clickPlay(v));
        this.pauseButton.setOnClickListener(v -> clickPause(v));
        this.goButton.setOnClickListener(v -> clickGo(v));
        this.clearButton.setOnClickListener(v -> clickClear(v));

    }


    //service stuff
    //start new service
    public void startNewService(View view){
        startService(new Intent(this,TimeService.class));
    }

    public void stopNewService(View view){
        stopService(new Intent(this,TimeService.class));
    }

    //helper stuff
    public void outputLine(String str){
        String output = (String) this.outputTextView.getText();
        this.outputTextView.setText(output + str + "\n");

    }

    public int getDelay(){
        String temp = this.delayEditText.toString();
        if(android.text.TextUtils.isDigitsOnly(temp)){
            return Integer.parseInt(temp);
        }
        return -1;
    }

    //click stuff
    public void clickPrev(View v){
        this.worker = new Worker(PREV);
        this.worker.start();
        Toast.makeText(getApplicationContext(),"Prev", Toast.LENGTH_SHORT).show();

        outputLine(PR);
    }
    public void clickNext(View v){
        this.worker = new Worker(NEXT);
        this.worker.start();
        Toast.makeText(getApplicationContext(),"Next",Toast.LENGTH_SHORT).show();

        outputLine(NE);
    }
    public void clickPlay(View v){
        this.worker = new Worker(PLAY);
        this.worker.start();
        Toast.makeText(getApplicationContext(),"Play",Toast.LENGTH_SHORT).show();

        outputLine(PL);
    }
    public void clickPause(View v){
        this.worker = new Worker(PAUSE);
        this.worker.start();
        Toast.makeText(getApplicationContext(),"Pause",Toast.LENGTH_SHORT).show();

        outputLine(PA);
    }
    public void clickGo(View v){
        //this.worker = new Worker(skip2);
        //this.worker.start();

        startNewService(v);

    }

    public void clickClear(View v){ //clear text output
        //Worker worker = new Worker(PAUSE);
        Toast.makeText(getApplicationContext(),"Clear",Toast.LENGTH_SHORT).show();
        //this.outputTextView.setText("Output\n");
        stopNewService(v);
    }

}
