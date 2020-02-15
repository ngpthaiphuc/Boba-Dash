package com.example.bubbletea;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView background, progress;
    LinearLayout textSplash, mainPage, instruction;
    TextView stepViewer;

    int numSteps;
    Button redeem;

    Animation appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background  = findViewById(R.id.background);
        textSplash  = findViewById(R.id.titleSplash);
        mainPage    = findViewById(R.id.mainTitle);
        stepViewer  = findViewById(R.id.stepViewer);
        instruction = findViewById(R.id.instruction);
        progress    = findViewById(R.id.progress_graphics);
        redeem      = findViewById(R.id.redeem);

        appear = AnimationUtils.loadAnimation(this, R.anim.abracadabra);

        background.animate().translationY(-2700).setDuration(800).setStartDelay(2000);
        textSplash.animate().alpha(0).setDuration(500).setStartDelay(2200);

        mainPage.startAnimation(appear);
        stepViewer.startAnimation(appear);
        instruction.startAnimation(appear);

        redeem.setVisibility(View.GONE);
        redeem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                progress.setImageResource(R.drawable.after_redeem);
            }
        });

        //Step counting
        SensorManager sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor stepSensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        numSteps = 0;
        sManager.registerListener(this, stepSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    // Need methods for sensor
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            numSteps++;
            stepViewer.setText("Number of Steps: " + numSteps);
            statusCheck();
        }
    }

    public void statusCheck(){
        if(numSteps > 100){
            progress.setImageResource(R.drawable.day_10);
            redeem.setVisibility(View.VISIBLE);
        } else if(numSteps > 90){
            progress.setImageResource(R.drawable.day_9_4);
        } else if(numSteps > 80){
            progress.setImageResource(R.drawable.day_8);
        } else if(numSteps > 70){
            progress.setImageResource(R.drawable.day_7);
        } else if(numSteps > 60){
            progress.setImageResource(R.drawable.day_6);
        } else if(numSteps > 50){
            progress.setImageResource(R.drawable.day_5);
        } else if(numSteps > 40){
            progress.setImageResource(R.drawable.day_4);
        } else if(numSteps > 30){
            progress.setImageResource(R.drawable.day_3);
        } else if(numSteps > 20){
            progress.setImageResource(R.drawable.day_2);
        } else if(numSteps > 10){
            progress.setImageResource(R.drawable.day_1);
        }
    }
}