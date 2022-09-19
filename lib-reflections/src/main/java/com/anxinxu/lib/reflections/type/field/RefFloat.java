package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefFloatField;

public class RefFloat extends BaseRefField implements IRefFloatField {

    public static final RefTypeFactory.Factory<RefFloat> CREATOR = new RefTypeFactory.Factory<RefFloat>() {
        @Override
        public RefFloat create(Class<RefFloat> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefFloat(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefFloat(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public float get(Object receiver) {
        return get(receiver, 0);
    }

    @Override
    public float get(Object receiver, float defValue) {
        try {
            setError(null);
            return getTarget().getFloat(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, float value) {
        try {
            setError(null);
            getTarget().setFloat(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
