package com.anxinxu.lib.reflection.android;

import android.app.Application;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArrayMap;

import com.anxinxu.lib.reflections.RefClass;
import com.anxinxu.lib.reflections.type.field.RefObject;
import com.anxinxu.lib.reflections.type.method.RefStaticMethod;

public class ActivityThreadReflection {

    public static Class<?> TYPE = RefClass.load(ActivityThreadReflection.class, "android.app.ActivityThread", true);

    public static RefStaticMethod<Object> currentActivityThread;
    public static RefStaticMethod<String> currentPackageName;
    public static RefStaticMethod<String> currentProcessName;
    public static RefStaticMethod<Application> currentApplication;
    public static RefStaticMethod<Object> getPackageManager;

    public static RefObject<Handler> mH;
    public static RefObject<Object> mAppThread;
    public static RefObject<ArrayMap<IBinder, Object>> mActivities;
    public static RefObject<ArrayMap<IBinder, Object>> mServices;
    public static RefObject<ArrayMap<IBinder, Object>> mLocalProviders;

}
