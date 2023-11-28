package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.codsoftalarmclock.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    TextView currentDate, currentTime;
    Handler handler;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dateFormat;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTime = findViewById(R.id.timeTextView);
        currentDate = findViewById(R.id.dateTextView);
        fab = findViewById(R.id.alarmButton);

        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        currentDate.setText(dateFormat.format(new Date()));

        fab.setOnClickListener(view -> {
            Intent iAlarm = new Intent(MainActivity.this, SetAlarm.class);
            startActivity(iAlarm);
        });

        handler = new Handler();
        updateCurrentTime();
    }

    private void updateCurrentTime() {
        currentTime.setText(timeFormat.format(new Date()));
        handler.postDelayed(this::updateCurrentTime, 1000); // Update every 1 second
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

