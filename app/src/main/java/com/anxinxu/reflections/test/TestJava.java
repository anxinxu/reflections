package com.anxinxu.reflections.test;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

public class TestJava {

    private static Activity sCurrentActivity;
    private static int sStatus;
    public static boolean sTestBoolean;
    static String sMsg;


    @NonNull
    public static String tosString() {
        return "Static TestJava{" +
                "sCurrentActivity=" + sCurrentActivity +
                ", sStatus=" + sStatus +
                ", sTestBoolean=" + sTestBoolean +
                ", sMsg='" + sMsg + '\'' +
                '}';
    }

    public static Activity getsCurrentActivity() {
        return sCurrentActivity;
    }

    public static void setsCurrentActivity(Activity sCurrentActivity) {
        TestJava.sCurrentActivity = sCurrentActivity;
    }

    public static int getsStatus() {
        return sStatus;
    }

    public static void setsStatus(int sStatus) {
        TestJava.sStatus = sStatus;
    }

    public static boolean issTestBoolean() {
        return sTestBoolean;
    }

    public static void setsTestBoolean(boolean sTestBoolean) {
        TestJava.sTestBoolean = sTestBoolean;
    }

    public static String getsMsg() {
        return sMsg;
    }

    public static void setsMsg(String sMsg) {
        TestJava.sMsg = sMsg;
    }

    private static String testsToString() {
        return tosString();
    }

    static void testStaticMethod(String msg, Object obj) {
        Log.e("anxintag", "testStaticMethod msg:" + msg + ", obj:" + obj);
    }

    private static void a(String msg, Object obj, Activity activity) {
        sCurrentActivity = activity;
        testStaticMethod(msg, obj);
    }

    public static void abc(TestJava java) {
        Log.e("anxintag", "abc " + java);
    }

    Object lock;
    protected int status;
    private boolean testBoolean;
    String msg;

    public TestJava() {
        this("null");
    }

    private TestJava(String msg) {
        this.msg = msg;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isTestBoolean() {
        return testBoolean;
    }

    public void setTestBoolean(boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @NonNull
    @Override
    public String toString() {
        return "TestJava{" +
                "lock=" + lock +
                ", status=" + status +
                ", testBoolean=" + testBoolean +
                ", msg='" + msg + '\'' +
                '}';
    }
}
