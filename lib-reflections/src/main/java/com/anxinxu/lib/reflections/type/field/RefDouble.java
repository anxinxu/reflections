package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefDoubleField;

public class RefDouble extends BaseRefField implements IRefDoubleField {

    public static final RefTypeFactory.Factory<RefDouble> CREATOR = new RefTypeFactory.Factory<RefDouble>() {
        @Override
        public RefDouble create(Class<RefDouble> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefDouble(targetClass, targetName, targetClassName, params);
        }
    };

    public RefDouble(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    public double get(Object receiver) {
        return get(receiver, 0);
    }

    @Override
    public double get(Object receiver, double defValue) {
        try {
            setError(null);
            return target.getDouble(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, double value) {
        try {
            setError(null);
            target.setDouble(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
