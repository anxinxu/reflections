package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefObjectField;

public class RefObject<T> extends BaseRefField implements IRefObjectField<T> {

    public static final RefTypeFactory.Factory<RefObject<?>> CREATOR = new RefTypeFactory.Factory<RefObject<?>>() {
        @Override
        public RefObject<?> create(Class<RefObject<?>> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefObject<>(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefObject(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public T get(Object receiver) {
        return get(receiver, null);
    }

    @Override
    public T get(Object receiver, T defValue) {
        try {
            setError(null);
            //noinspection unchecked
            return (T) getTarget().get(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, T value) {
        try {
            setError(null);
            getTarget().set(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
