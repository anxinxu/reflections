package com.anxinxu.lib.reflections.type.base.api.field.s;

public interface IRefObjectStaticField<T> {

    T get();

    T get(T defValue);

    boolean set(T value);
}
