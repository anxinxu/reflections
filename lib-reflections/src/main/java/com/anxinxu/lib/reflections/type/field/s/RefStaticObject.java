package com.anxinxu.lib.reflections.type.field.s;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticField;
import com.anxinxu.lib.reflections.type.base.api.field.s.IRefObjectStaticField;
import com.anxinxu.lib.reflections.type.field.RefObject;

import java.lang.reflect.Field;

public class RefStaticObject<T> extends BaseRefStaticField<RefObject<T>> implements IRefObjectStaticField<T> {

    public static final RefTypeFactory.Factory<RefStaticObject<?>> CREATOR = new RefTypeFactory.Factory<RefStaticObject<?>>() {
        @Override
        public RefStaticObject<?> create(Class<RefStaticObject<?>> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
            return new RefStaticObject<>(RefTypeFactory.create(RefObject.class, targetClass, targetName, targetClassName, params));
        }
    };

    public RefStaticObject(RefObject<T> targetProxy) {
        super(targetProxy);
    }

    @Override
    public T get() {
        return targetProxy.get(null);
    }

    @Override
    public T get(T defValue) {
        return targetProxy.get(null, defValue);
    }

    @Override
    public boolean set(T value) {
        return targetProxy.set(null, value);
    }
}
