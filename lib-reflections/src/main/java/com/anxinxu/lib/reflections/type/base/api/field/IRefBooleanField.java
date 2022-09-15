package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefBooleanField {

    boolean get(Object receiver);

    boolean get(Object receiver, boolean defValue);

    boolean set(Object receiver, boolean value);
}
