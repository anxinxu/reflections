package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.ReflectUtil;

import java.lang.reflect.Field;

public abstract class BaseRefField extends BaseRef<Field> {


    public BaseRefField(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    protected final Field getTarget(String name) {
        return ReflectUtil.getFieldOrNull(targetClass, name);
    }

    public Class<?> fieldType() {
        return target != null ? target.getType() : null;
    }

    public Field field() {
        return target;
    }

    public String fieldName() {
        return targetName;
    }

    public Class<?> targetClass() {
        return targetClass;
    }
}
