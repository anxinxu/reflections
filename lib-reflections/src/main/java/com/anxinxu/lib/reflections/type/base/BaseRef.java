package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.type.base.api.IRefType;

import java.io.FileNotFoundException;

abstract public class BaseRef<T> implements IRefType {

    protected final Class<?> targetClass;
    protected final String targetClassName;
    protected final String targetName;
    protected final Class<?>[] params;
    private T target;
    private Throwable error;
    private boolean isLoadTarget = false;

    public BaseRef(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        this.targetClass = targetClass;
        this.targetClassName = targetClassName;
        this.targetName = targetName;
        this.params = params;
        if (!lazyLoadTarget) {
            setupTarget();
        }
    }

    private void setupTarget() {
        if (isLoadTarget) {
            return;
        }
        synchronized (this) {
            if (isLoadTarget) {
                return;
            }
            if (targetClass == null) {
                target = null;
                error = new ClassNotFoundException("not found class for " + targetClassName);
            } else {
                target = getTarget(targetName);
                if (target == null) {
                    error = new FileNotFoundException("not fount target for " + targetName);
                }
            }
            isLoadTarget = true;
        }
    }

    abstract protected T getTarget(String name);

    public Throwable getError() {
        setupTarget();
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public boolean isSucceed() {
        setupTarget();
        return error == null;
    }

    public boolean isReflectSucceed() {
        return getTarget() != null;
    }

    public T getTarget() {
        setupTarget();
        return target;
    }
}
