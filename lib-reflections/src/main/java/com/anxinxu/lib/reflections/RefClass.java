package com.anxinxu.lib.reflections;

import com.anxinxu.lib.reflections.type.RefTypeFactory;
import com.anxinxu.lib.reflections.type.base.api.IRefType;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class RefClass {

    public static Class<?> load(Class<?> mappingClass, String targetClassName, boolean lazyLoadTarget) {
        try {
            return load(mappingClass, Class.forName(targetClassName), targetClassName, null, lazyLoadTarget);
        } catch (Exception e) {
            return load(mappingClass, null, targetClassName, null, lazyLoadTarget);
        }
    }

    public static Class<?> load(Class<?> mappingClass, Class<?> targetClass, boolean lazyLoadTarget) {
        if (targetClass == null) {
            return load(mappingClass, null, "null", null, lazyLoadTarget);
        }
        return load(mappingClass, targetClass, targetClass.getName(), null, lazyLoadTarget);
    }

    public static Class<?> load(Class<?> mappingClass, Object mappingObj, Class<?> targetClass, boolean lazyLoadTarget) {
        if (targetClass == null) {
            return load(mappingClass, null, "null", mappingObj, lazyLoadTarget);
        }
        return load(mappingClass, targetClass, targetClass.getName(), mappingObj, lazyLoadTarget);
    }

    public static Class<?> load(Class<?> mappingClass, Object mappingObj, String targetClassName, boolean lazyLoadTarget) {
        try {
            return load(mappingClass, Class.forName(targetClassName), targetClassName, mappingObj, lazyLoadTarget);
        } catch (Exception e) {
            return load(mappingClass, null, targetClassName, mappingObj, lazyLoadTarget);
        }
    }

    public static Class<?> load(Object mappingObj, Class<?> targetClass, boolean lazyLoadTarget) {
        if (targetClass == null) {
            return load(mappingObj.getClass(), null, "null", mappingObj, lazyLoadTarget);
        }
        return load(mappingObj.getClass(), targetClass, targetClass.getName(), mappingObj, lazyLoadTarget);
    }

    public static Class<?> load(Object mappingObj, String targetClassName, boolean lazyLoadTarget) {
        try {
            return load(mappingObj.getClass(), Class.forName(targetClassName), targetClassName, mappingObj, lazyLoadTarget);
        } catch (Exception e) {
            return load(mappingObj.getClass(), null, targetClassName, mappingObj, lazyLoadTarget);
        }
    }

    private static Class<?> load(Class<?> mappingClass, Class<?> targetClass, String targetClassName, Object mappingObj, boolean lazyLoadTarget) {
        Field[] fields = mappingClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (Modifier.isStatic(field.getModifiers()) || mappingObj != null) {
                    Class<?> fieldType = field.getType();
                    if (IRefType.class.isAssignableFrom(fieldType)) {
                        //noinspection unchecked
                        Class<? extends IRefType> type = (Class<? extends IRefType>) fieldType;
                        IRefType obj = RefTypeFactory.create(type, targetClass, getTargetName(field), targetClassName, getParameterTypesByField(field), lazyLoadTarget);
                        // for kotlin companion object or object
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        field.set(mappingObj, obj);
                    }
                }
            } catch (Exception e) {
                // Ignore
                e.printStackTrace();
            }
        }
        return targetClass;
    }

    private static String getTargetName(Field field) {
        return field.isAnnotationPresent(ReflectionName.class) ? field.getAnnotation(ReflectionName.class).value() : field.getName();
    }

    private static Class<?>[] getParameterTypesByField(Field field) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        if (field.isAnnotationPresent(MethodParams.class)) {
            Class<?>[] types = field.getAnnotation(MethodParams.class).value();
            if (types == null || types.length == 0) {
                return null;
            }
            ClassLoader targetClassLoader = RefClass.class.getClassLoader();
            for (int i = 0; i < types.length; i++) {
                Class<?> clazz = types[i];
                if (clazz.getClassLoader() == targetClassLoader) {
                    Class.forName(clazz.getName());
                    Class<?> realClass = (Class<?>) clazz.getField("TYPE").get(null);
                    types[i] = realClass;
                }
            }
            return types;
        } else if (field.isAnnotationPresent(MethodReflectParams.class)) {
            String[] typeNames = field.getAnnotation(MethodReflectParams.class).value();
            return ReflectUtil.toClasses(typeNames);
        } else {
            return null;
        }
    }


}