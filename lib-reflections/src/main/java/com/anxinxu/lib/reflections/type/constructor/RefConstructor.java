package com.anxinxu.lib.reflections.type.constructor;

import com.anxinxu.lib.reflections.ReflectUtil;
import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRef;
import com.anxinxu.lib.reflections.type.base.api.IRefConstructor;

import java.lang.reflect.Constructor;

public class RefConstructor<T> extends BaseRef<Constructor<?>> implements IRefConstructor<T> {

    public static final RefTypeFactory.Factory<RefConstructor<?>> CREATOR = new RefTypeFactory.Factory<RefConstructor<?>>() {
        @Override
        public RefConstructor<?> create(Class<RefConstructor<?>> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefConstructor<>(targetClass, targetName, targetClassName, params);
        }
    };

    public RefConstructor(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    protected Constructor<?> getTarget(String name) {
        try {
            Class<?>[] types = params;
            if (types == null || types.length == 0) {
                return ReflectUtil.getConstructorOrNull(targetClass);
            } else {
                return ReflectUtil.getConstructorOrNull(targetClass, types);
            }
        } catch (Throwable throwable) {
            return null;
        }
    }

    @Override
    public T newInstance(Object... params) {
        return newInstance(null, params);
    }

    @Override
    public T newInstance(T defValue, Object... params) {
        try {
            setError(null);
            if (params == null || params.length == 0) {
                //noinspection unchecked
                return (T) target.newInstance();
            }
            //noinspection unchecked
            return (T) target.newInstance(params);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }
}
