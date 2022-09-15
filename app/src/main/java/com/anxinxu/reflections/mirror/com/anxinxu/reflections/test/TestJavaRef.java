package com.anxinxu.reflections.mirror.com.anxinxu.reflections.test;

import android.app.Activity;

import com.anxinxu.lib.reflections.MethodParams;
import com.anxinxu.lib.reflections.MethodReflectParams;
import com.anxinxu.lib.reflections.RefClass;
import com.anxinxu.lib.reflections.ReflectionName;
import com.anxinxu.lib.reflections.type.constructor.RefConstructor;
import com.anxinxu.lib.reflections.type.field.RefBoolean;
import com.anxinxu.lib.reflections.type.field.RefInt;
import com.anxinxu.lib.reflections.type.field.RefObject;
import com.anxinxu.lib.reflections.type.field.s.RefStaticBoolean;
import com.anxinxu.lib.reflections.type.field.s.RefStaticInt;
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject;
import com.anxinxu.lib.reflections.type.method.RefStaticMethod;
import com.anxinxu.reflections.test.TestJava;

public class TestJavaRef {

    public static Class<?> TYPE = RefClass.load(TestJavaRef.class, com.anxinxu.reflections.test.TestJava.class);

    @ReflectionName("sCurrentActivity")
    public static RefStaticObject<Object> a;
    public static RefStaticObject<Activity> sCurrentActivity;
    public static RefStaticInt sStatus;
    public static RefStaticBoolean sTestBoolean;
    public static RefStaticObject<String> sMsg;

    public static RefObject<Object> lock;
    public static RefInt status;
    public static RefBoolean testBoolean;
    public static RefObject<String> msg;

    @MethodParams(String.class)
    public static RefConstructor<TestJava> constructor;

    public static RefStaticMethod<String> testsToString;
    @MethodParams({String.class, Object.class})
    public static RefStaticMethod<Void> testStaticMethod;
    @MethodParams({String.class, Object.class, Activity.class})
    @ReflectionName("a")
    public static RefStaticMethod<Void> setActivityAndLog;

    @MethodReflectParams({"com.anxinxu.reflections.test.TestJava"})
    public static RefStaticMethod<Void> abc;
}
