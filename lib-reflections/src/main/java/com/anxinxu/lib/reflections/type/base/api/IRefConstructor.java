package com.anxinxu.lib.reflections.type.base.api;

public interface IRefConstructor<T> {

    T newInstance(Object... params);
    T newInstanceOrDefault(T defValue, Object... params);
}
