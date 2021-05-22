package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver {
    private static final String TAG = MySmsReceiver.class.getSimpleName();
    public static final String PDU_TYPE = "pdus";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        LocationHandler locationHandler = new LocationHandler(context, null);
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");

        Object[] pdus = (Object[]) bundle.get(PDU_TYPE);



        if (pdus != null){
            System.err.println("pdus != null");
            boolean isVersionM = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);

            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++){
                if (isVersionM){
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                }else {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                strMessage += "SMS from" + msgs[i].getOriginatingAddress();
                strMessage += " :" +  msgs[i].getMessageBody() + "\n";



                if (msgs[i].getOriginatingAddress().equals("+37066371655")){
                    if (msgs[i].getMessageBody().contains("turn on data")){

                    }
                    if (msgs[i].getMessageBody().contains("getLocation")){

                        locationHandler.setNumber(msgs[i].getOriginatingAddress());
                        locationHandler.sendLocation();
                    }
                }

                Log.d(TAG, "onReceive: " + strMessage);
                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
            }


        }


    }
}