package com.anxinxu.lib.reflections.type.base.api.field;

public interface IRefByteField {

    byte get(Object receiver);

    byte get(Object receiver, byte defValue);

    boolean set(Object receiver, byte value);
}
