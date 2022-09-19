package com.anxinxu.lib.reflection.android;

import android.os.IInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceHandler implements InvocationHandler {

    private final IInterface originService;
    private final ServiceManagerReflection.ServiceCall call;

    public ServiceHandler(IInterface originService, ServiceManagerReflection.ServiceCall call) {
        this.originService = originService;
        this.call = call;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (call == null) {
            return method.invoke(originService, args);
        }
        boolean needCallOrigin = call.beforeCall(originService, method, args);
        Object originReturn = null;
        if (needCallOrigin) {
            originReturn = method.invoke(originService, args);
        }
        ServiceManagerReflection.ServiceCall.Result result = call.afterCall(originService, method, args, originReturn);
        return result != null && result.hooked ? result.getResult() : needCallOrigin ? originReturn : method.invoke(originService, args);
    }
}
