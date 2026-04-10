package com.sys.sync;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Оно просто запускается и закрывается за 0.1 сек.
        // Юзер даже не поймет, что что-то открылось.
        finish();
    }
}
