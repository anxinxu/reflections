package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefLongField;

public class RefLong extends BaseRefField implements IRefLongField {

    public static final RefTypeFactory.Factory<RefLong> CREATOR = new RefTypeFactory.Factory<RefLong>() {
        @Override
        public RefLong create(Class<RefLong> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefLong(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefLong(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public long get(Object receiver) {
        return get(receiver, 0);
    }

    @Override
    public long get(Object receiver, long defValue) {
        try {
            setError(null);
            return getTarget().getLong(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, long value) {
        try {
            setError(null);
            getTarget().setLong(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
