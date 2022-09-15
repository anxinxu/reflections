package com.anxinxu.lib.reflections.type.base;

import com.anxinxu.lib.reflections.ReflectUtil;

import java.lang.reflect.Method;

public class BaseRefMethod extends BaseRef<Method> {


    public BaseRefMethod(Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params) {
        super(targetClass, targetName, targetClassName, params);
    }

    @Override
    protected Method getTarget(String name) {
        try {
            Class<?>[] types = params;
            if (types == null) {
                return ReflectUtil.getMethodOrNull(targetClass, name, types);
            } else {
                return ReflectUtil.getMethodByName(targetClass, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Method method() {
        return target;
    }

    public Class<?> returnType() {
        return target != null ? target.getReturnType() : null;
    }

    public Class<?>[] params() {
        return params;
    }

    public String methodName() {
        return targetName;
    }

    public Class<?> targetClass() {
        return targetClass;
    }
}
