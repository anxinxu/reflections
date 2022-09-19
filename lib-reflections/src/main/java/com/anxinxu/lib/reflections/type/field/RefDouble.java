package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefDoubleField;

public class RefDouble extends BaseRefField implements IRefDoubleField {

    public static final RefTypeFactory.Factory<RefDouble> CREATOR = new RefTypeFactory.Factory<RefDouble>() {
        @Override
        public RefDouble create(Class<RefDouble> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefDouble(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefDouble(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public double get(Object receiver) {
        return get(receiver, 0);
    }

    @Override
    public double get(Object receiver, double defValue) {
        try {
            setError(null);
            return getTarget().getDouble(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, double value) {
        try {
            setError(null);
            getTarget().setDouble(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
