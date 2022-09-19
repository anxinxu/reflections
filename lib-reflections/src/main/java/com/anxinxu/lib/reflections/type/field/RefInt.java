package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefIntField;

public class RefInt extends BaseRefField implements IRefIntField {

    public static final RefTypeFactory.Factory<RefInt> CREATOR = new RefTypeFactory.Factory<RefInt>() {
        @Override
        public RefInt create(Class<RefInt> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefInt(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefInt(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public int get(Object receiver) {
        return get(receiver, 0);
    }

    @Override
    public int get(Object receiver, int defValue) {
        try {
            setError(null);
            return getTarget().getInt(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, int value) {
        try {
            setError(null);
            getTarget().setInt(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
