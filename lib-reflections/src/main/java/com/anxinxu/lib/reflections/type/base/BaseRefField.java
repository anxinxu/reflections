package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.ReflectUtil;

import java.lang.reflect.Field;

public abstract class BaseRefField extends BaseRef<Field> {


    public BaseRefField(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        super(targetClass, targetName, targetClassName, params, lazyLoadTarget);
    }

    @Override
    protected final Field getTarget(String name) {
        return ReflectUtil.getFieldOrNull(targetClass, name);
    }

    public Class<?> fieldType() {
        return getTarget() != null ? getTarget().getType() : null;
    }

    public Field field() {
        return getTarget();
    }

    public String fieldName() {
        return targetName;
    }

    public Class<?> targetClass() {
        return targetClass;
    }
}
