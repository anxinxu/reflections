package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.type.base.api.IRefType;

import java.lang.reflect.Method;

public class BaseRefStaticMethod<T extends BaseRefMethod> implements IRefType {

    protected final T targetProxy;

    public BaseRefStaticMethod(T targetProxy) {
        this.targetProxy = targetProxy;
    }

    public Throwable getError() {
        return targetProxy.getError();
    }

    public void setError(Throwable error) {
        targetProxy.setError(error);
    }

    public Method method() {
        return targetProxy.method();
    }

    public Class<?> returnType() {
        return targetProxy.returnType();
    }

    public Class<?>[] params() {
        return targetProxy.params();
    }

    public String methodName() {
        return targetProxy.methodName();
    }

    public Class<?> targetClass() {
        return targetProxy.targetClass();
    }

    public boolean isSucceed() {
        return targetProxy.isSucceed();
    }

    public boolean isReflectSucceed() {
        return targetProxy.isReflectSucceed();
    }
}
