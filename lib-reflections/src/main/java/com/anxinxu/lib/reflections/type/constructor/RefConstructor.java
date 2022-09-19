package com.anxinxu.lib.reflections.type.constructor;

import com.anxinxu.lib.reflections.ReflectUtil;
import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRef;
import com.anxinxu.lib.reflections.type.base.api.IRefConstructor;

import java.lang.reflect.Constructor;

public class RefConstructor<T> extends BaseRef<Constructor<T>> implements IRefConstructor<T> {

    public static final RefTypeFactory.Factory<RefConstructor<?>> CREATOR = new RefTypeFactory.Factory<RefConstructor<?>>() {
        @Override
        public RefConstructor<?> create(Class<RefConstructor<?>> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefConstructor<>(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefConstructor(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    protected Constructor<T> getTarget(String name) {
        try {
            Class<?>[] types = params;
            if (types == null || types.length == 0) {
                //noinspection unchecked
                return (Constructor<T>) ReflectUtil.getConstructorOrNull(targetClass);
            } else {
                //noinspection unchecked
                return (Constructor<T>) ReflectUtil.getConstructorOrNull(targetClass, types);
            }
        } catch (Throwable throwable) {
            return null;
        }
    }

    @Override
    public T newInstance(Object... params) {
        return newInstanceOrDefault(null, params);
    }

    @Override
    public T newInstanceOrDefault(T defValue, Object... params) {
        try {
            setError(null);
            if (params == null || params.length == 0) {
                return getTarget().newInstance();
            }
            return getTarget().newInstance(params);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    public Constructor<T> constructor() {
        return getTarget();
    }

    public Class<?>[] params() {
        return params;
    }

    public Class<?> targetClass() {
        return targetClass;
    }
}
