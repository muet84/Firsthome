package com.example.mibrahim.firsthome;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button bind,unbind, rebind;
    Intent bindserviceintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        rebind = (Button) findViewById(R.id.rebind);
        rebind.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        bindserviceintent = new Intent(NotificationActivity.this, NotificationService.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bind){
        startService(new Intent(NotificationActivity.this,NotificationService.class));
        bindService(bindserviceintent,serviceConnection, Context.BIND_NOT_FOREGROUND);
        }
        else if(v.getId()==R.id.unbind){
             unbindService(serviceConnection);

        }
        else if (v.getId() == R.id.rebind) {
            stopService(new Intent(NotificationActivity.this,NotificationService.class));
        }
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(NotificationActivity.this, "Connected", Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(NotificationActivity.this, "Dis-Connected", Toast.LENGTH_SHORT).show();
         }
    };

}
