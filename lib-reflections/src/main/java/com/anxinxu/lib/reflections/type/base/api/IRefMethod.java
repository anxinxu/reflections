package com.anxinxu.lib.reflections.type.base.api;

public interface IRefMethod<T> {

    T invoke(Object receiver, Object... args);

    T invoke(Object receiver, T defValue, Object... args);
}
