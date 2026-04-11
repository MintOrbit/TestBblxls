package com.android.system.core.security;

import android.app.Activity;
import android.os.Bundle;

public class ComposeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish(); // Сразу закрываемся, если кто-то попытается открыть
    }
}
