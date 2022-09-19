package com.anxinxu.lib.reflection.android;

import com.anxinxu.lib.reflections.MethodParams;
import com.anxinxu.lib.reflections.RefClass;
import com.anxinxu.lib.reflections.type.method.RefMethod;
import com.anxinxu.lib.reflections.type.method.RefStaticMethod;

public class VMRuntimeReflection {

    public static Class<?> TYPE = RefClass.load(VMRuntimeReflection.class, "dalvik.system.VMRuntime", true);

    public static RefStaticMethod<Object> getRuntime;

    public static RefMethod<String> bootClassPath;
    public static RefMethod<String> classPath;
    public static RefMethod<String> vmVersion;
    public static RefMethod<String> vmLibrary;
    public static RefMethod<String> vmInstructionSet;
    public static RefMethod<Boolean> is64Bit;
    public static RefMethod<Integer> getTargetSdkVersion;
    @MethodParams(String[].class)
    public static RefMethod<Void> setHiddenApiExemptions;

}
