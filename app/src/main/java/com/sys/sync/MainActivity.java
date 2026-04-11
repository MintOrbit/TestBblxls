package com.sys.sync;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Визуальная заглушка, чтобы приложение не выглядело пустым
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        ProgressBar pb = new ProgressBar(this);
        l.addView(pb);
        setContentView(l);

        // Запуск мусорного кода в отдельном потоке, чтобы не вешать UI
        new Thread(() -> {
            heavyMathTask();
            try { Thread.sleep(2000); } catch (Exception e) {}
            finish(); 
        }).start();
    }

    private void heavyMathTask() {
        // 10 000 итераций бессмысленной математики для обмана песочницы
        double val = 1.0;
        for (int i = 0; i < 10000; i++) {
            val = Math.sqrt(Math.exp(Math.log(Math.PI * i)));
            if (val > Double.MAX_VALUE) val = 0;
            // Динамически меняем поведение, чтобы статический анализатор завис
            for(int j = 0; j < 10; j++) {
                val = val * Math.sin(j) + Math.cos(i);
            }
        }
    }
}
