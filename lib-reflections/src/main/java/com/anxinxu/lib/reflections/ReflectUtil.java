package com.anxinxu.lib.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {

    public static Field getField(Class<?> cls, String name) throws NoSuchFieldException, SecurityException {
        Field field = cls.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static Field getFieldOrNull(Class<?> cls, String name) {
        try {
            return getField(cls, name);
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static Method getMethod(Class<?> cls, String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        Method method = cls.getDeclaredMethod(name, parameterTypes);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method;
    }

    public static Method getMethodOrNull(Class<?> cls, String name, Class<?>... parameterTypes) {
        try {
            return getMethod(cls, name, parameterTypes);
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static Method getMethodByName(Class<?> cls, String name) {
        Method method = null;
        for (Method m : cls.getDeclaredMethods()) {
            if (name.equals(m.getName())) {
                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }
                method = m;
                break;
            }
        }
        return method;
    }

    public static <T> Constructor<T> getConstructor(Class<T> cls, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        Constructor<T> constructor = cls.getDeclaredConstructor(parameterTypes);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor;
    }

    public static <T> Constructor<T> getConstructorOrNull(Class<T> cls, Class<?>... parameterTypes) {
        try {
            return getConstructor(cls, parameterTypes);
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static Class<?> getProtoType(String typeName) {
        if (typeName.equals("int")) {
            return Integer.TYPE;
        }
        if (typeName.equals("long")) {
            return Long.TYPE;
        }
        if (typeName.equals("boolean")) {
            return Boolean.TYPE;
        }
        if (typeName.equals("byte")) {
            return Byte.TYPE;
        }
        if (typeName.equals("short")) {
            return Short.TYPE;
        }
        if (typeName.equals("char")) {
            return Character.TYPE;
        }
        if (typeName.equals("float")) {
            return Float.TYPE;
        }
        if (typeName.equals("double")) {
            return Double.TYPE;
        }
        if (typeName.equals("void")) {
            return Void.TYPE;
        }
        return null;
    }

    public static Class<?>[] toClasses(String[] typeNames) throws ClassNotFoundException {
        if (typeNames == null || typeNames.length == 0) {
            return null;
        }
        Class<?>[] types = new Class<?>[typeNames.length];
        for (int i = 0; i < typeNames.length; i++) {
            Class<?> type = ReflectUtil.getProtoType(typeNames[i]);
            if (type == null) {
                type = Class.forName(typeNames[i]);
            }
            types[i] = type;
        }
        return types;
    }
}
