package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefLongStaticField;
import com.anxinxu.lib.reflections.type.field.RefLong;

public class RefStaticLong extends BaseRefStaticField<RefLong> implements IRefLongStaticField {

    public static final RefTypeFactory.Factory<RefStaticLong> CREATOR = new RefTypeFactory.Factory<RefStaticLong>() {
        @Override
        public RefStaticLong create(Class<RefStaticLong> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticLong(RefTypeFactory.create(RefLong.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticLong(RefLong targetProxy) {
        super(targetProxy);
    }

    @Override
    public long get() {
        return targetProxy.get(null);
    }

    @Override
    public long get(long defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(long value) {
        return targetProxy.set(null, value);
    }
}
