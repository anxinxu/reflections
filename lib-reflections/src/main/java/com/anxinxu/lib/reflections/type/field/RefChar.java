package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefCharField;

public class RefChar extends BaseRefField implements IRefCharField {

    public static final RefTypeFactory.Factory<RefChar> CREATOR = new RefTypeFactory.Factory<RefChar>() {
        @Override
        public RefChar create(Class<RefChar> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefChar(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefChar(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public char get(Object receiver) {
        return get(receiver, '0');
    }

    @Override
    public char get(Object receiver, char defValue) {
        try {
            setError(null);
            return getTarget().getChar(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, char value) {
        try {
            setError(null);
            getTarget().setChar(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
