package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefFloatField {

    float get(Object receiver);

    float get(Object receiver, float defValue);

    boolean set(Object receiver, float value);
}
