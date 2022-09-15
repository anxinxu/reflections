package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefShortStaticField;
import com.anxinxu.lib.reflections.type.field.RefShort;

public class RefStaticShort extends BaseRefStaticField<RefShort> implements IRefShortStaticField {

    public static final RefTypeFactory.Factory<RefStaticShort> CREATOR = new RefTypeFactory.Factory<RefStaticShort>() {
        @Override
        public RefStaticShort create(Class<RefStaticShort> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticShort(RefTypeFactory.create(RefShort.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticShort(RefShort targetProxy) {
        super(targetProxy);
    }

    @Override
    public short get() {
        return targetProxy.get(null);
    }

    @Override
    public short get(short defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(short value) {
        return targetProxy.set(null, value);
    }
}
