package com.example.androidtest1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

public class WatchVideos {
    static String TAG = "testc";
    private static final Context mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private static final UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    static int h = mDevice.getDisplayHeight();
    static int w = mDevice.getDisplayWidth();

    @Test
    public void watchDouyin() {
//        home_search("抖音极速版");
//        com.ss.android.ugc.aweme.lite/com.ss.android.ugc.aweme.splash.SplashActivity
        String lsStr=execCommand("ls");
        MLog.v(TAG,"lsStr="+lsStr);

    }

    public String execCommand(String command) {
        Log.d("testc", "execCommand---");
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = runtime.exec(command);
            proc.waitFor();
            Log.v("testc", "---go---");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line + " ");
            }
            Log.d("testc", stringBuffer.toString());
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return null;
    }
    public static void startAPPNotClick(String packageName) {
        PackageManager pm = mContext.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        intent.getCategories().contains("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        mContext.startActivity(intent);
    }

    public static void home_search(String text) {
        int page = 4;
        int t = 1;
        mDevice.pressHome();
        UiObject app_name = new UiObject(new UiSelector().className("android.widget.TextView").description(text));
        for (int i = 0; i < page; i++) {
            if (!app_name.exists()) {
                mDevice.swipe(w * 3 / 4, h / 2, w / 4, h / 2, 10);
                sleep(t);
            } else {
                UiObject button = new UiObject(new UiSelector().className("android.widget.TextView").text(text));
                try {
                    button.clickAndWaitForNewWindow(2);
                } catch (UiObjectNotFoundException e) {
                    Log.v("testc", "UiObjectNotFoundException e=" + e.toString());
                }
                sleep(t);
                break;
            }
        }
    }

    public static void sleep(int x) {
        try {
            Thread.sleep(x * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
