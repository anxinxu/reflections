package com.anxinxu.lib.reflection.android;

import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BinderHandler implements InvocationHandler {

    private final IBinder originBinder;
    private final Class<?> serviceInterface;
    private final ServiceHandler handler;

    public BinderHandler(IBinder originBinder, Class<?> serviceInterface, ServiceHandler handler) {
        this.originBinder = originBinder;
        this.serviceInterface = serviceInterface;
        this.handler = handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if ("queryLocalInterface".equals(name)) {
            return Proxy.newProxyInstance(originBinder.getClass().getClassLoader(), new Class[]{IBinder.class, IInterface.class, serviceInterface}, handler);
        }
        return method.invoke(originBinder, args);
    }
}
