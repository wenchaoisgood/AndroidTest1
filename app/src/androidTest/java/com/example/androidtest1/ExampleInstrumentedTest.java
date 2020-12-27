package com.example.androidtest1;

import android.app.ExpandableListActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.UiWatcher;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static androidx.test.uiautomator.Until.findObject;

/**fd
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    static String TAG = "testc";
    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private VoiceControlBroadcast voiceControlBroadcast;
    public static final String BROADCAST_ACTION_DISC = "com.example.androidtest1.VoiceControlBroadcast";
    int displayHeight = mDevice.getDisplayHeight();
    int displayWidth = mDevice.getDisplayWidth();

    @Test
    public void test1() {
        MLog.v(TAG, "test1");
        Bundle extras = InstrumentationRegistry.getArguments();
        String peerID = null;
        peerID = extras.getString("peerID");
        String peerID2 = extras.getString("peerID2");
        MLog.v(TAG, "peerID=" + peerID + "peerID2=" + peerID2);

        UiObject moreBtn = new UiObject(new UiSelector().description("设置"));
        try {
            moreBtn.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        //uiautomator查看工具，只有Scrollable显示为true的，都是可以支持滑动的界面
        UiScrollable RecyclerView = new UiScrollable(new UiSelector().resourceId("android:id/list"));
        RecyclerView.setMaxSearchSwipes(6);//设置最大可扫动次数
        UiObject Security = new UiObject(new UiSelector().text("用户手册"));//找到“更多”这个选项
        try {
            RecyclerView.scrollIntoView(Security);//然后点击 它（更多）
            Security.clickAndWaitForNewWindow();
            UiObject exitbtn = new UiObject(new UiSelector().text("退出"));//找到“更多”这个选项
            exitbtn.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }




    private void startDeskTopApp(String appName) {
        mDevice.pressHome();
        UiObject appObj = new UiObject(new UiSelector().description(appName));
        int pageCount = 6;
        for (int findCount = 0; findCount < pageCount; findCount++) {
            if (appObj.exists()) {
                try {
                    appObj.clickAndWaitForNewWindow();
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                mDevice.swipe(500, 500, 100, 500, 15);
            }
        }
    }

    private void initBroadCast() {
        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        voiceControlBroadcast = new VoiceControlBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction(BROADCAST_ACTION_DISC);// 只有持有相同的action的接受者才能接收此广播
        // 3. 动态注册：调用Context的registerReceiver（）方法
        new ExpandableListActivity().registerReceiver(voiceControlBroadcast, intentFilter);
    }

    private void regesterWatch() {
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mDevice.registerWatcher("x1", new UiWatcher() {
            UiObject warrning = new UiObject(new UiSelector().textContains("跳过"));

            @Override
            public boolean checkForCondition() {
                Log.v(TAG, "the watcher is begin 1!");
                if (warrning.exists()) {
                    Log.v(TAG, "the watcher is begin 2!");
                    UiObject noremind = new UiObject(new UiSelector().text("跳过"));
                    try {
                        noremind.click();
                    } catch (UiObjectNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    UiObject allow = new UiObject(new UiSelector().text("允许"));
                    try {
                        allow.click();
                    } catch (UiObjectNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    System.out.println("it is allow");
                    return true;
                }
                System.out.println("it is refuse");
                return false;
            }
        });
        mDevice.runWatchers();//此行为强制运行监听，正常使用请删除
    }

    private void timeSleep(double time) {

        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            Log.e(TAG, "exception!!e=" + e.toString());
        }
    }

    private boolean isClickByDecs(String decs, long timeOut) {
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        UiObject2 uiObject2 = mDevice.wait(findObject(By.descContains(decs)), timeOut);

        if (uiObject2 != null) {
            uiObject2.click();
            return false;
        } else {
            Log.e(TAG, "NOTFOUND");
        }
        return false;
    }
}
