package com.anxinxu.lib.reflections.type.method;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.BaseRefStaticMethod;
import com.anxinxu.lib.reflections.type.base.api.IRefStaticMethod;

public class RefStaticMethod<T> extends BaseRefStaticMethod<RefMethod<T>> implements IRefStaticMethod<T> {

    public static final RefTypeFactory.Factory<RefStaticMethod<?>> CREATOR = new RefTypeFactory.Factory<RefStaticMethod<?>>() {
        @Override
        public RefStaticMethod<?> create(Class<RefStaticMethod<?>> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
            return new RefStaticMethod<>(RefTypeFactory.create(RefMethod.class, targetClass, targetName, targetClassName, params, lazyLoadTarget));
        }
    };

    public RefStaticMethod(RefMethod<T> targetProxy) {
        super(targetProxy);
    }

    @Override
    public T invoke(Object... args) {
        return targetProxy.invoke(null, args);
    }

    @Override
    public T invoke(T defValue, Object... args) {
        return targetProxy.invoke(null, defValue, args);
    }

    public Class<?>[] getParameterTypes() {
        return targetProxy.getParameterTypes();
    }

}
