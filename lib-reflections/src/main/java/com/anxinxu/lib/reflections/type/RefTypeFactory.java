package com.anxinxu.lib.reflections.type;

import com.anxinxu.lib.reflections.type.base.api.IRefType;
import com.anxinxu.lib.reflections.type.constructor.RefConstructor;
import com.anxinxu.lib.reflections.type.field.RefBoolean;
import com.anxinxu.lib.reflections.type.field.RefByte;
import com.anxinxu.lib.reflections.type.field.RefChar;
import com.anxinxu.lib.reflections.type.field.RefDouble;
import com.anxinxu.lib.reflections.type.field.RefFloat;
import com.anxinxu.lib.reflections.type.field.RefInt;
import com.anxinxu.lib.reflections.type.field.RefLong;
import com.anxinxu.lib.reflections.type.field.RefObject;
import com.anxinxu.lib.reflections.type.field.RefShort;
import com.anxinxu.lib.reflections.type.field.s.RefStaticBoolean;
import com.anxinxu.lib.reflections.type.field.s.RefStaticByte;
import com.anxinxu.lib.reflections.type.field.s.RefStaticChar;
import com.anxinxu.lib.reflections.type.field.s.RefStaticDouble;
import com.anxinxu.lib.reflections.type.field.s.RefStaticFloat;
import com.anxinxu.lib.reflections.type.field.s.RefStaticInt;
import com.anxinxu.lib.reflections.type.field.s.RefStaticLong;
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject;
import com.anxinxu.lib.reflections.type.field.s.RefStaticShort;
import com.anxinxu.lib.reflections.type.method.RefMethod;
import com.anxinxu.lib.reflections.type.method.RefStaticMethod;

import java.util.HashMap;

public class RefTypeFactory {

    private static final HashMap<Class<?>, Factory<? extends IRefType>> REF_TYPES = new HashMap<>();

    static {
        REF_TYPES.put(RefBoolean.class, RefBoolean.CREATOR);
        REF_TYPES.put(RefByte.class, RefByte.CREATOR);
        REF_TYPES.put(RefChar.class, RefChar.CREATOR);
        REF_TYPES.put(RefShort.class, RefShort.CREATOR);
        REF_TYPES.put(RefInt.class, RefInt.CREATOR);
        REF_TYPES.put(RefLong.class, RefLong.CREATOR);
        REF_TYPES.put(RefFloat.class, RefFloat.CREATOR);
        REF_TYPES.put(RefDouble.class, RefDouble.CREATOR);
        REF_TYPES.put(RefObject.class, RefObject.CREATOR);

        REF_TYPES.put(RefStaticBoolean.class, RefStaticBoolean.CREATOR);
        REF_TYPES.put(RefStaticByte.class, RefStaticByte.CREATOR);
        REF_TYPES.put(RefStaticChar.class, RefStaticChar.CREATOR);
        REF_TYPES.put(RefStaticShort.class, RefStaticShort.CREATOR);
        REF_TYPES.put(RefStaticInt.class, RefStaticInt.CREATOR);
        REF_TYPES.put(RefStaticLong.class, RefStaticLong.CREATOR);
        REF_TYPES.put(RefStaticFloat.class, RefStaticFloat.CREATOR);
        REF_TYPES.put(RefStaticDouble.class, RefStaticDouble.CREATOR);
        REF_TYPES.put(RefStaticObject.class, RefStaticObject.CREATOR);

        REF_TYPES.put(RefMethod.class, RefMethod.CREATOR);
        REF_TYPES.put(RefStaticMethod.class, RefStaticMethod.CREATOR);

        REF_TYPES.put(RefConstructor.class, RefConstructor.CREATOR);
    }

    public static <T extends IRefType> void addFactory(Class<T> clz, Factory<T> factory) {
        REF_TYPES.put(clz, factory);
    }

    public static <T extends IRefType> T create(Class<T> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget) {
        //noinspection unchecked
        Factory<T> f = (Factory<T>) REF_TYPES.get(fieldType);
        T res = null;
        if (f != null) {
            res = f.create(fieldType, targetClass, targetName, targetClassName, params, lazyLoadTarget);
        }
        if (res == null) {
            // error
            System.out.println("RefTypeFactory create null");
        }
        return res;
    }

    public interface Factory<T extends IRefType> {
        T create(Class<T> fieldType, Class<?> targetClass, String targetName, String targetClassName, Class<?>[] params, boolean lazyLoadTarget);
    }
}
