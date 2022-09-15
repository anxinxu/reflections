package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefFloatStaticField;
import com.anxinxu.lib.reflections.type.field.RefFloat;

public class RefStaticFloat extends BaseRefStaticField<RefFloat> implements IRefFloatStaticField {

    public static final RefTypeFactory.Factory<RefStaticFloat> CREATOR = new RefTypeFactory.Factory<RefStaticFloat>() {
        @Override
        public RefStaticFloat create(Class<RefStaticFloat> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticFloat(RefTypeFactory.create(RefFloat.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticFloat(RefFloat targetProxy) {
        super(targetProxy);
    }

    @Override
    public float get() {
        return targetProxy.get(null);
    }

    @Override
    public float get(float defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(float value) {
        return targetProxy.set(null, value);
    }
}
