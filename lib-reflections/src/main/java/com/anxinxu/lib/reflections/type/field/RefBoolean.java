package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefBooleanField;

public class RefBoolean extends BaseRefField implements IRefBooleanField {

    public static final RefTypeFactory.Factory<RefBoolean> CREATOR = new RefTypeFactory.Factory<RefBoolean>() {
        @Override
        public RefBoolean create(Class<RefBoolean> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefBoolean(targetClass, targetName, targetClassName, params);
        }
    };

    public RefBoolean(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    public boolean get(Object receiver) {
        return get(receiver, false);
    }

    @Override
    public boolean get(Object receiver, boolean defValue) {
        try {
            setError(null);
            return target.getBoolean(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, boolean value) {
        try {
            setError(null);
            target.setBoolean(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            // Ignore
            return false;
        }
    }


}
