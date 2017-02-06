package com.example.mibrahim.firsthome;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by M.Ibrahim on 1/28/2017.
 */
public class NotificationService extends Service {

    Timer timer;
    Binder binder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(NotificationService.this, "Service Start", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(NotificationService.this, "Start Command", Toast.LENGTH_SHORT).show();

        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(NotificationService.this, "On Bind", Toast.LENGTH_SHORT).show();
        Toast.makeText(NotificationService.this, "New Client Bound"  + intent.getStringExtra("servicedata"), Toast.LENGTH_SHORT).show();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
             startNotification();

            }

        }, 5000, 5000);

        return binder ;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(NotificationService.this, "Unbind", Toast.LENGTH_SHORT).show();
        timer.cancel();
       // stopSelf();
        return true;

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(NotificationService.this, "On Rebind", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        timer.cancel();
//      Toast.makeText(NotificationService.this, "stop", Toast.LENGTH_SHORT).show();

        Log.i("stop","On Destroy");

    }

    void  startNotification(){

        Intent gotoNotification = new Intent(NotificationService.this,NotificationActivity.class);
        gotoNotification.putExtra("title","This is Data is Intent");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,gotoNotification,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        Notification notification;
        notification = notificationBuilder.setContentTitle("My Title")
                .setContentText("Content Text")
                .setAutoCancel(true)
                .setTicker("Ticker")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);

    }


    public class MyBinder extends Binder {
        public NotificationService getBinder(){

            return NotificationService.this;
        }



    }

}
