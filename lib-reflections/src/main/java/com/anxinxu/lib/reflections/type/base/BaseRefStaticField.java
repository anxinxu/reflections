package com.anxinxu.lib.reflections.type.base;


import com.anxinxu.lib.reflections.type.base.api.IRefType;

import java.lang.reflect.Field;

public class BaseRefStaticField<T extends BaseRefField> implements IRefType {

    protected final T targetProxy;

    public BaseRefStaticField(T targetProxy) {
        this.targetProxy = targetProxy;
    }

    public Throwable getError() {
        return targetProxy.getError();
    }

    public void setError(Throwable error) {
        targetProxy.setError(error);
    }

    public Class<?> fieldType() {
        return targetProxy.fieldType();
    }

    public Field field() {
        return targetProxy.getTarget();
    }

    public String fieldName() {
        return targetProxy.fieldName();
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
