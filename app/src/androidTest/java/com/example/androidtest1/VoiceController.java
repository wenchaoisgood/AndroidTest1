package com.example.androidtest1;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class VoiceController {
    static String TAG = "testc";
    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private static final Context mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    public static final String BROADCAST_ACTION_DISC = "com.example.androidtest1.VoiceControlBroadcast";

    @Test
    public void testRun() {
        Looper.prepare();
        Toast.makeText(mContext, "11111", Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
