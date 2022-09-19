package com.anxinxu.lib.reflections.type.field;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefField;
import com.anxinxu.lib.reflections.type.base.api.field.IRefByteField;

public class RefByte extends BaseRefField implements IRefByteField {

    public static final RefTypeFactory.Factory<RefByte> CREATOR = new RefTypeFactory.Factory<RefByte>() {
        @Override
        public RefByte create(Class<RefByte> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefByte(targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
    };

    public RefByte(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    public byte get(Object receiver) {
        return get(receiver, (byte) 0);
    }

    @Override
    public byte get(Object receiver, byte defValue) {
        try {
            setError(null);
            return getTarget().getByte(receiver);
        } catch (Exception e) {
            setError(e);
            return defValue;
        }
    }

    @Override
    public boolean set(Object receiver, byte value) {
        try {
            setError(null);
            getTarget().setByte(receiver, value);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }
}
