package com.sys.sync;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        
        TextView txt = new TextView(this);
        txt.setText("Синхронизация системных компонентов...");
        layout.addView(txt);
        
        ProgressBar pb = new ProgressBar(this);
        layout.addView(pb);
        
        setContentView(layout);
    }
}
