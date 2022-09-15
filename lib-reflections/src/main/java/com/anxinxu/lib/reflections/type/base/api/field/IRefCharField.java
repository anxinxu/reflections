package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefCharField {

    char get(Object receiver);

    char get(Object receiver, char defValue);

    boolean set(Object receiver, char value);
}
