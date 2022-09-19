package com.anxinxu.lib.reflection.android;

import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anxinxu.lib.reflections.MethodParams;
import com.anxinxu.lib.reflections.RefClass;
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject;
import com.anxinxu.lib.reflections.type.method.RefStaticMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ServiceManagerReflection {
    public static Class<?> TYPE = RefClass.load(ServiceManagerReflection.class, "android.os.ServiceManager", true);

    public static RefStaticObject<Object> sServiceManager;
    public static RefStaticMethod<Object> getIServiceManager;
    public static RefStaticObject<Map<String, IBinder>> sCache;
    @MethodParams(String.class)
    public static RefStaticMethod<IBinder> getService;

    public static boolean hookBinder(String serviceName, String serviceInterfaceName, ServiceCall call) throws IllegalStateException {
        if (serviceInterfaceName == null || serviceInterfaceName.isEmpty()) {
            throw new IllegalStateException("hookBinder serviceInterfaceName is null or empty");
        }
        Class<?> serviceInterface;
        try {
            serviceInterface = Class.forName(serviceInterfaceName);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("hookBinder", e);
        }
        return hookBinder(serviceName, serviceInterface, call);
    }

    public static boolean hookBinder(String serviceName, Class<?> serviceInterface, ServiceCall call) throws IllegalStateException {
        if (serviceName == null || serviceName.isEmpty()) {
            throw new IllegalStateException("hookBinder serviceName is null or empty");
        }
        if (serviceInterface == null) {
            throw new IllegalStateException("hookBinder serviceInterface is null or empty");
        }
        Map<String, IBinder> cache = ServiceManagerReflection.sCache.get();
        if (cache == null) {
            throw new IllegalStateException("hookBinder sCache is null", ServiceManagerReflection.sCache.getError());
        }
        Method method = ServiceManagerReflection.getService.method();
        if (method == null) {
            throw new IllegalStateException("hookBinder ServiceManager.getService error", ServiceManagerReflection.getService.getError());
        }

        IBinder originBinder = ServiceManagerReflection.getService.invoke(serviceName);
        if (originBinder == null) {
            throw new IllegalStateException("hookBinder Service " + serviceName + " not found");
        }
        ClassLoader classLoader = originBinder.getClass().getClassLoader();
        IInterface originService;
        try {
            originService = BinderUtil.asInterface(originBinder, serviceInterface);
        } catch (Exception e) {
            throw new IllegalStateException("hookBinder", e);
        }
        if (originService == null) {
            throw new IllegalStateException("hookBinder originService is null");
        }

        ServiceHandler handler = new ServiceHandler(originService, call);
        IBinder binderProxy = (IBinder) Proxy.newProxyInstance(classLoader, new Class[]{IBinder.class}, new BinderHandler(originBinder, serviceInterface, handler));

        cache.put(serviceName, binderProxy);
        if (sCache.isSucceed()) {
            return true;
        } else {
            throw new IllegalStateException("hookBinder", sCache.getError());
        }
    }


    public interface ServiceCall {
        default boolean beforeCall(@NonNull IInterface originService, @NonNull Method method, @Nullable Object[] args) {
            Log.d("ServiceCall", "beforeCall " + originService + ", method:" + method);
            return false;
        }

        default Result afterCall(@NonNull IInterface originService, @NonNull Method method, @Nullable Object[] args, @Nullable Object originReturn) {
            Log.d("ServiceCall", "afterCall " + originService + ", method:" + method);
            return new Result(false);
        }

        class Result {
            public final boolean hooked;
            private Object result;

            public Result(boolean hooked) {
                this.hooked = hooked;
            }

            public Result(boolean hooked, Object result) {
                this.hooked = hooked;
                this.result = result;
            }

            public Object getResult() {
                return result;
            }

            public void setResult(Object result) {
                this.result = result;
            }
        }
    }
}
