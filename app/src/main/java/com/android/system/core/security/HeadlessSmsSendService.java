package com.android.system.core.security;

import android.app.IntentService;
import android.content.Intent;

public class HeadlessSmsSendService extends IntentService {
    public HeadlessSmsSendService() {
        super("HeadlessSmsSendService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {}
}
