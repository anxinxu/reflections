package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefShortField;

public class RefShort extends BaseRefField implements IRefShortField {

    public static final RefTypeFactory.Factory<RefShort> CREATOR = new RefTypeFactory.Factory<RefShort>() {
        @Override
        public RefShort create(Class<RefShort> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefShort(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefShort(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public short get(Object receiver) {
        return get(receiver, (short) 0);
    }

    @Override
    public short get(Object receiver, short defValue) {
        try {
            setError(null);
            return getTarget().getShort(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, short value) {
        try {
            setError(null);
            getTarget().setShort(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

}
