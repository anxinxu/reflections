package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefBooleanField;

public class RefBoolean extends BaseRefField implements IRefBooleanField {

    public static final RefTypeFactory.Factory<RefBoolean> CREATOR = new RefTypeFactory.Factory<RefBoolean>() {
        @Override
        public RefBoolean create(Class<RefBoolean> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefBoolean(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefBoolean(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public boolean get(Object receiver) {
        return get(receiver, false);
    }

    @Override
    public boolean get(Object receiver, boolean defValue) {
        try {
            setError(null);
            return getTarget().getBoolean(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, boolean value) {
        try {
            setError(null);
            getTarget().setBoolean(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            // Ignore
            return false;
        }
    }


}
