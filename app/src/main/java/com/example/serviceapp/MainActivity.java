package com.example.serviceapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        TextView txtStaus;
        IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStaus = findViewById(R.id.txt_status);
        intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.MAIN_ACTION);
        intentFilter.addAction(MusicService.PLAY_ACTION);
        intentFilter.addAction(MusicService.PREV_ACTION);
        intentFilter.addAction(MusicService.NEXT_ACTION);
        intentFilter.addAction(MusicService.STOPFOREGROUND_ACTION);
        intentFilter.addAction(MusicService.STARTFOREGROUND_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(musicReciever, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(musicReciever);
    }

    public void startService(View view) {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        intent.setAction(MusicService.STARTFOREGROUND_ACTION);
        startService(intent);


    }

    public void stopService(View view) {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        intent.setAction(MusicService.STOPFOREGROUND_ACTION);
        startService(intent);
    }

    BroadcastReceiver musicReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action;
            action = intent.getAction();
            txtStaus.setText(action);
        }
    };

}

