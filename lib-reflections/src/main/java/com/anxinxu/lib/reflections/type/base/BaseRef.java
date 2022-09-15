package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.type.base.api.IRefType;

import java.io.FileNotFoundException;

abstract public class BaseRef<T> implements IRefType {

    protected final Class<?> targetClass;
    protected final String targetClassName;
    protected final String targetName;
    protected final Class<?>[] params;
    protected final T target;
    private Throwable error;

    public BaseRef(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        this.targetClass = targetClass;
        this.targetClassName = targetClassName;
        this.targetName = targetName;
        this.params = params;
        if (targetClass == null) {
            target = null;
            error = new ClassNotFoundException("not found class for " + targetClassName);
        } else {
            target = getTarget(targetName);
            if (target == null) {
                error = new FileNotFoundException("not fount target for " + targetName);
            }
        }
    }

    abstract protected T getTarget(String name);

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
