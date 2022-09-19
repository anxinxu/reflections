package com.anxinxu.lib.reflection.android;

import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.Method;

public class BinderUtil {

    public static IInterface asInterface(IBinder binder, Class<?> serviceInterface) throws Exception {
        return asInterface(binder, serviceInterface.getName());
    }

    public static IInterface asInterface(IBinder binder, String serviceInterfaceName) throws Exception {
        if (serviceInterfaceName == null || serviceInterfaceName.isEmpty()) {
            throw new IllegalArgumentException("serviceInterfaceName is null or empty");
        }

        Class<?> stubClass = Class.forName(serviceInterfaceName + "$Stub");
        Method asInterface = stubClass.getDeclaredMethod("asInterface", IBinder.class);
        return (IInterface) asInterface.invoke(null, binder);
    }

}
