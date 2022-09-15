package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefLongField {

    long get(Object receiver);

    long get(Object receiver, long defValue);

    boolean set(Object receiver, long value);
}
