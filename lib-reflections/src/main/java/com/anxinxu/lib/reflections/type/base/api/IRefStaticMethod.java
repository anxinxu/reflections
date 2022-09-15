package com.anxinxu.lib.reflections.type.base.api;

public interface IRefStaticMethod<T> {

    T invoke(Object... args);

    T invoke(T defValue, Object... args);
}
