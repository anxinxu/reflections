package com.anxinxu.reflections;

import static android.os.Build.VERSION.SDK_INT;

import android.os.Build;

import java.lang.reflect.Method;


public class Reflection {

    private static final String TAG = "Reflection";

    private static Object   sVmRuntime;
    private static Method   setHiddenApiExemptions;
    private static Class<?> activityThreadClass;
    private static Object   activityThread;

    static {
        if (SDK_INT >= Build.VERSION_CODES.P) {
            try {
                Method forName = Class.class.getDeclaredMethod("forName", String.class);
                Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);

                Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
                Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
                setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
                sVmRuntime = getRuntime.invoke(null);
            } catch (Throwable e) {
//                Timber.e(e, "reflect bootstrap failed:");
            }
        }
    }

    /**
     * make the method exempted from hidden API check.
     *
     * @param method the method signature prefix.
     * @return true if success.
     */
    public static boolean exempt(String method) {
        return exempt(new String[]{method});
    }

    /**
     * make specific methods exempted from hidden API check.
     *
     * @param methods the method signature prefix, such as "Ldalvik/system", "Landroid" or even "L"
     * @return true if success
     */
    public static boolean exempt(String... methods) {
        if (sVmRuntime == null || setHiddenApiExemptions == null) {
            return false;
        }

        try {
            setHiddenApiExemptions.invoke(sVmRuntime, new Object[]{methods});
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * Make all hidden API exempted.
     *
     * @return true if success.
     */
    public static boolean exemptAll() {
        return exempt(new String[]{"L"});
    }

    public static Class<?> getActivityThreadClass() {
        if (activityThreadClass == null) {
            try {
                activityThreadClass = Class.forName("android.app.ActivityThread");
            } catch (Throwable throwable) {
//                Timber.e(throwable);
            }
        }
        return activityThreadClass;
    }

    public static Object getActivityThread() {
        if (activityThread == null) {
            try {
                activityThread = getActivityThreadClass().getDeclaredMethod("currentActivityThread").invoke(null);
            } catch (Throwable throwable) {
//                Timber.e(throwable);
            }
        }
        return activityThread;
    }

    /**
     * @param clazz      class
     * @param name       method name
     * @param parameters method params
     * @return method return
     * @throws Throwable exception
     */
    public static Object invokeStaticMethod(String clazz, String name, Object... parameters) throws Throwable {
        Class<?> cls = Class.forName(clazz);
        final Class<?>[] parameterTypes = new Class[parameters.length];
        for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
            Object o = parameters[i];
            parameterTypes[i] = o.getClass();
        }
        Method method = cls.getMethod(name, parameterTypes);
        method.setAccessible(true);
        return method.invoke(null, parameters);
    }
}
