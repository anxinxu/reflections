package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefObjectField<T> {

    T get(Object receiver);

    T get(Object receiver, T defValue);

    boolean set(Object receiver, T value);
}
