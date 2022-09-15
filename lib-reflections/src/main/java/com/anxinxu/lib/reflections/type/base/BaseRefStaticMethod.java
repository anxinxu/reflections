package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.type.base.api.IRefType;

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
}
