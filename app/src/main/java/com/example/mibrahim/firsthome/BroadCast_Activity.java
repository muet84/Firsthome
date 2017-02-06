package com.example.mibrahim.firsthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BroadCast_Activity extends AppCompatActivity {

    Button btn_general,btn_vibration,btn_silent,btn_show_current_mode,btn_broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        btn_broadcast = (Button) findViewById(R.id.btn_set_broadcast);
        btn_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
                MyBroadCast myBroadCast = new MyBroadCast();
                registerReceiver(myBroadCast,intentFilter);

            }
        });


    }


}
