package com.example.androidtest1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

public class VoiceControlBroadcast extends BroadcastReceiver {
    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private static final Context mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Override
    public void onReceive(Context context, Intent intent) {
        //收到广播后的操作
        Log.v("testc", "111111111111111111111111");
        int displayHeight = mDevice.getDisplayHeight();
        int displayWidth = mDevice.getDisplayWidth();
        mDevice.swipe(displayWidth / 2, displayHeight * 2 / 3, displayWidth / 2, displayHeight / 10, 9);
    }
}
