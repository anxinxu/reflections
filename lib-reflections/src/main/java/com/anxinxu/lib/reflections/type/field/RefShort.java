package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefShortField;

public class RefShort extends BaseRefField implements IRefShortField {

    public static final RefTypeFactory.Factory<RefShort> CREATOR = new RefTypeFactory.Factory<RefShort>() {
        @Override
        public RefShort create(Class<RefShort> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefShort(targetClass, targetName, targetClassName, params);
        }
    };

    public RefShort(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    public short get(Object receiver) {
        return get(receiver, (short) 0);
    }

    @Override
    public short get(Object receiver, short defValue) {
        try {
            setError(null);
            return target.getShort(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, short value) {
        try {
            setError(null);
            target.setShort(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

}
