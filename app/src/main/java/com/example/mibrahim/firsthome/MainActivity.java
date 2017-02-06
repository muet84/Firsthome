package com.example.mibrahim.firsthome;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Dialog dialog;
    Button dialog_cancel_btn,dialog_btn_reload, smsBtn, btn_show;
    Button showdialog,notify,browser,showDetails,btn_audio;
    ImageView img;
    DatePicker datePicker;
    DbHelper dbHelper;
    RecyclerView recyclerView;
    ArrayList<String> list;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        notify = (Button) findViewById(R.id.notify);
        notify.setOnClickListener(this);
        browser = (Button) findViewById(R.id.web);
        browser.setOnClickListener(this);
     /* sms button declaration */
       smsBtn = (Button) findViewById(R.id.sms_btn);
       smsBtn.setOnClickListener(this);

        /* End Button declaration */
     showDetails = (Button) findViewById(R.id.details_btn);
     showDetails.setOnClickListener(this);

     btn_audio = (Button) findViewById(R.id.audio_btn);
     btn_audio.setOnClickListener(this);



        list = new ArrayList<>();
        for(int a =0; a<20; a++){
            list.add("This is Index" + a);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter(this,list));

//        btn_show = (Button) findViewById(R.id.btn_show_data);
//        btn_show.setOnClickListener(this);
//        showdialog = (Button) findViewById(R.id.btn_show_dialog);
//        showdialog.setOnClickListener(this);
//        datePicker = (DatePicker) findViewById(R.id.date_picker);
//
//        int day = datePicker.getDayOfMonth();
//        Toast.makeText(MainActivity.this, ""+day, Toast.LENGTH_SHORT).show();
//
//
//        dialog = new Dialog(this);
//        dialog.setTitle("My Dialog");
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.something);
//
//        dialog_cancel_btn = (Button) dialog.findViewById(R.id.btn);
//        dialog_btn_reload = (Button) dialog.findViewById(R.id.btn_reload);
//        img = (ImageView)dialog.findViewById(R.id.img_dialog);
//
//        dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Image Touched", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        dialog_btn_reload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//                Toast.makeText(MainActivity.this, "App Restarted", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_show_dialog){
            dbHelper = new DbHelper(this);
            dbHelper.InsertCategory("Tech",null,"0");

        }else if(v.getId()==R.id.btn_show_data){
            dbHelper = new DbHelper(this);
            dbHelper.ShowCategries();
        }
        else if(v.getId()==R.id.notify){
           // startNotification();
            startService(new Intent(MainActivity.this,NotificationService.class));
            startActivity(new Intent(MainActivity.this,NotificationActivity.class));
        }else if(v.getId()==R.id.web){
            /*Intent gotoNotification = new Intent(MainActivity.this,NotificationActivity.class);*/
            Intent swithToWeb = new Intent(MainActivity.this, WebActivity.class);
            startActivity(swithToWeb);
        }else if(v.getId()==R.id.sms_btn){
            /*Intent gotoNotification = new Intent(MainActivity.this,NotificationActivity.class);*/
        Intent swithToSms = new Intent(MainActivity.this, ShortmessageActivity.class);
        startActivity(swithToSms);
        }else if(v.getId()==R.id.details_btn){
            showDetails();
        }else if(v.getId()==R.id.audio_btn){
            startActivity(new Intent(MainActivity.this,BroadCast_Activity.class));
        }



    }

    private void showDetails() {

        String  details =  "VERSION.RELEASE : "+Build.VERSION.RELEASE
                +"\nVERSION.INCREMENTAL : "+Build.VERSION.INCREMENTAL
                +"\nVERSION.SDK.NUMBER : "+Build.VERSION.SDK_INT
                +"\nBOARD : "+Build.BOARD
                +"\nBOOTLOADER : "+Build.BOOTLOADER
                +"\nBRAND : "+Build.BRAND
                +"\nCPU_ABI : "+Build.CPU_ABI
                +"\nCPU_ABI2 : "+Build.CPU_ABI2
                +"\nDISPLAY : "+Build.DISPLAY
                +"\nFINGERPRINT : "+Build.FINGERPRINT
                +"\nHARDWARE : "+Build.HARDWARE
                +"\nHOST : "+Build.HOST
                +"\nID : "+Build.ID
                +"\nMANUFACTURER : "+Build.MANUFACTURER
                +"\nMODEL : "+Build.MODEL
                +"\nPRODUCT : "+Build.PRODUCT
                +"\nSERIAL : "+Build.SERIAL
                +"\nTAGS : "+Build.TAGS
                +"\nTIME : "+Build.TIME
                +"\nTYPE : "+ Build.TYPE
                +"\nUNKNOWN : "+Build.UNKNOWN
                +"\nUSER : "+Build.USER;


        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = "IMEI"+manager.getDeviceId();
        String company = "MSISDN "+manager.getLine1Number();
        boolean roaming = manager.isNetworkRoaming();
        String country = "Country"+manager.getNetworkCountryIso();
        int phone =manager.getPhoneType();
        String subscriberId = "IMSI NUMBER "+ manager.getSubscriberId();
        String m = "One"+manager.getNetworkOperatorName()+ "\n"+"Two"+ manager.getNetworkOperator();

        String sound = getSoundProfile();


        Log.i("Device Details", details + "\n" + IMEI + "\n" + company + "\n" + "Roaming" + roaming + "\n" + country + "\n" + "phone" + phone + "Company" + m);
        Log.i("sound",sound);
        setSpeakerState(true);


    }

    void  startNotification(){

        Intent gotoNotification = new Intent(MainActivity.this,NotificationActivity.class);
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
        notificationManager.notify(1, notification);
    }
    public String getSoundProfile(){

        try{
        String MODE_NORMAL="Normal",MODE_SILENT="Silent",MODE_VIBRATE="Vibration";
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int profileMode = audioManager.getRingerMode();


            if(profileMode == AudioManager.RINGER_MODE_NORMAL)
                return MODE_NORMAL;
            else if(profileMode == AudioManager.RINGER_MODE_SILENT)
                return MODE_SILENT;
            else if(profileMode == AudioManager.RINGER_MODE_VIBRATE)
                return MODE_VIBRATE;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public void setSpeakerState(boolean on){
        try{
            final AudioManager audioManager =
                    (AudioManager)getSystemService(Context.AUDIO_SERVICE);

            if(audioManager != null)
                audioManager.setSpeakerphoneOn(on);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

