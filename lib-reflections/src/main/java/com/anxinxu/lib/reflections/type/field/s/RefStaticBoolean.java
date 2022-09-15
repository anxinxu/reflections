package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefBooleanStaticField;
import com.anxinxu.lib.reflections.type.field.RefBoolean;

public class RefStaticBoolean extends BaseRefStaticField<RefBoolean> implements IRefBooleanStaticField {

    public static final RefTypeFactory.Factory<RefStaticBoolean> CREATOR = new RefTypeFactory.Factory<RefStaticBoolean>() {
        @Override
        public RefStaticBoolean create(Class<RefStaticBoolean> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticBoolean(RefTypeFactory.create(RefBoolean.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticBoolean(RefBoolean targetProxy) {
        super(targetProxy);
    }

    @Override
    public boolean get() {
        return targetProxy.get(null);
    }

    @Override
    public boolean get(boolean defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(boolean value) {
        return targetProxy.set(null, value);
    }
}
