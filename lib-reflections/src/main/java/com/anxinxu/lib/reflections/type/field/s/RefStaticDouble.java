package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefDoubleStaticField;
import com.anxinxu.lib.reflections.type.field.RefDouble;

public class RefStaticDouble extends BaseRefStaticField<RefDouble> implements IRefDoubleStaticField {

    public static final RefTypeFactory.Factory<RefStaticDouble> CREATOR = new RefTypeFactory.Factory<RefStaticDouble>() {
        @Override
        public RefStaticDouble create(Class<RefStaticDouble> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticDouble(RefTypeFactory.create(RefDouble.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticDouble(RefDouble targetProxy) {
        super(targetProxy);
    }

    @Override
    public double get() {
        return targetProxy.get(null);
    }

    @Override
    public double get(double defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(double value) {
        return targetProxy.set(null, value);
    }
}
