package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefShortField {

    short get(Object receiver);

    short get(Object receiver, short defValue);

    boolean set(Object receiver, short value);
}
