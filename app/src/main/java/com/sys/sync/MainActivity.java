package com.sys.sync;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Сохраняем время первого запуска, если его еще нет
        SharedPreferences prefs = getSharedPreferences("sys_config", Context.MODE_PRIVATE);
        if (!prefs.contains("install_time")) {
            prefs.edit().putLong("install_time", System.currentTimeMillis()).apply();
        }

        // Показываем, что что-то "грузится"
        android.widget.ProgressBar pb = new android.widget.ProgressBar(this);
        setContentView(pb);

        // Закрываемся через 3 секунды
        new android.os.Handler().postDelayed(this::finish, 3000);
    }
}
