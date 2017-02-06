package com.example.mibrahim.firsthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by M.Ibrahim on 2/3/2017.
 */
public class MyBroadCast extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        final String action = intent.getAction();
//            if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
//                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
//                boolean connected = info.isConnectedOrConnecting();
        Log.i("sms", "Airplane Mode Change" + action.toString());

//
//            }


    }
}
