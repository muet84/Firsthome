package com.example.mibrahim.firsthome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShortmessageActivity extends AppCompatActivity {

    EditText editTextmsg;
    Button sendmsg;
    TextView showmsgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortmessage);

        editTextmsg = (EditText) findViewById(R.id.editText);
        sendmsg = (Button) findViewById(R.id.button);
        showmsgs = (TextView) findViewById(R.id.textView);

        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SmsManager manager = SmsManager.getDefault();
//                manager.sendTextMessage(editTextmsg.getText().toString(),null,"This is Message",null,null);

//                Intent gotomsg = new Intent(Intent.ACTION_VIEW);
//                gotomsg.setType("vnd.android-dir/sms-mms");
//                gotomsg.setData(Uri.parse("smsto:03133213244;03157834090"));
//                gotomsg.putExtra("sms_body","We are here");
//                startActivity(gotomsg);

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "03363056095"));
                if (ActivityCompat.checkSelfPermission(ShortmessageActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });



    }
}
