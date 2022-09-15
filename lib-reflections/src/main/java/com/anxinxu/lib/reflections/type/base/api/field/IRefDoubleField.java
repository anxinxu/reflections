package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefDoubleField {

    double get(Object receiver);

    double get(Object receiver, double defValue);

    boolean set(Object receiver, double value);
}
