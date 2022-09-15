package com.anxinxu.lib.reflections.type.method;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefMethod;
import com.anxinxu.lib.reflections.type.base.api.IRefMethod;

public class RefMethod<T> extends BaseRefMethod implements IRefMethod<T> {

    public static final RefTypeFactory.Factory<RefMethod<?>> CREATOR = new RefTypeFactory.Factory<RefMethod<?>>() {
        @Override
        public RefMethod<?> create(Class<RefMethod<?>> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefMethod<>(targetClass, targetName, targetClassName, params);
        }
    };

    public RefMethod(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    public T invoke(Object receiver, Object... args) {
        return invoke(receiver, null, args);
    }

    @Override
    public T invoke(Object receiver, T defValue, Object... args) {
        try {
            setError(null);
            //noinspection unchecked
            return (T) target.invoke(receiver, args);
        } catch (Throwable throwable) {
            setError(throwable);
            return defValue;
        }
    }

    public Class<?>[] getParameterTypes() {
        if (target == null) return null;
        return target.getParameterTypes();
    }
}
