package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefCharStaticField;
import com.anxinxu.lib.reflections.type.field.RefChar;

public class RefStaticChar extends BaseRefStaticField<RefChar> implements IRefCharStaticField {

    public static final RefTypeFactory.Factory<RefStaticChar> CREATOR = new RefTypeFactory.Factory<RefStaticChar>() {
        @Override
        public RefStaticChar create(Class<RefStaticChar> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticChar(RefTypeFactory.create(RefChar.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticChar(RefChar targetProxy) {
        super(targetProxy);
    }

    @Override
    public char get() {
        return targetProxy.get(null);
    }

    @Override
    public char get(char defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(char value) {
        return targetProxy.set(null, value);
    }
}
