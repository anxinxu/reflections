package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefByteStaticField;
import com.anxinxu.lib.reflections.type.field.RefByte;

public class RefStaticByte extends BaseRefStaticField<RefByte> implements IRefByteStaticField {

    public static final RefTypeFactory.Factory<RefStaticByte> CREATOR = new RefTypeFactory.Factory<RefStaticByte>() {
        @Override
        public RefStaticByte create(Class<RefStaticByte> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticByte(RefTypeFactory.create(RefByte.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticByte(RefByte targetProxy) {
        super(targetProxy);
    }

    @Override
    public byte get() {
        return targetProxy.get(null);
    }

    @Override
    public byte get(byte defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(byte value) {
        return targetProxy.set(null, value);
    }
}
