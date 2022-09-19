package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefIntStaticField;
import com.anxinxu.lib.reflections.type.field.RefInt;

public class RefStaticInt extends BaseRefStaticField<RefInt> implements IRefIntStaticField {

    public static final RefTypeFactory.Factory<RefStaticInt> CREATOR = new RefTypeFactory.Factory<RefStaticInt>() {
        @Override
        public RefStaticInt create(Class<RefStaticInt> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefStaticInt(RefTypeFactory.create(RefInt.class, targetClass, targetName, targetClassName, params, lazyLoadTarget));
        }
    };

    public RefStaticInt(RefInt targetProxy) {
        super(targetProxy);
    }

    @Override
    public int get() {
        return targetProxy.get(null);
    }

    @Override
    public int get(int defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(int value) {
        return targetProxy.set(null, value);
    }
}
