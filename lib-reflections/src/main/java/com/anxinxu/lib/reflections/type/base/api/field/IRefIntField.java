package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefIntField {

    int get(Object receiver);

    int get(Object receiver, int defValue);

    boolean set(Object receiver, int value);
}
