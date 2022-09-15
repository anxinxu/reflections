package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefCharField;

public class RefChar extends BaseRefField implements IRefCharField {

    public static final RefTypeFactory.Factory<RefChar> CREATOR = new RefTypeFactory.Factory<RefChar>() {
        @Override
        public RefChar create(Class<RefChar> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefChar(targetClass, targetName, targetClassName, params);
        }
    };

    public RefChar(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    public char get(Object receiver) {
        return get(receiver, '0');
    }

    @Override
    public char get(Object receiver, char defValue) {
        try {
            setError(null);
            return target.getChar(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, char value) {
        try {
            setError(null);
            target.setChar(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
