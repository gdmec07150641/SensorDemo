package com.example.shana.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{
    private TextView ts1,ts2,ts3;
    private Sensor s1,s2,s3;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initView();
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        s1=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        s2=sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        s3=sm.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    private void initView() {
        ts1= (TextView) findViewById(R.id.s1);
        ts2= (TextView) findViewById(R.id.s2);
        ts3= (TextView) findViewById(R.id.s3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,s1,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,s2,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,s3,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            float x=sensorEvent.values[SensorManager.DATA_X];
        float y=sensorEvent.values[SensorManager.DATA_Y];
        float z=sensorEvent.values[SensorManager.DATA_Z];
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION){
            ts2.setText(x+","+y+","+z);
        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
            ts3.setText(x+","+y+","+z);
        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ts1.setText(x+","+y+","+z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
