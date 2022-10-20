package com.anxinxu.reflections.mirror.com.anxinxu.reflections.test;

import com.anxinxu.lib.reflections.MethodParams;
import com.anxinxu.lib.reflections.RefClass;
import com.anxinxu.lib.reflections.ReflectionName;
import com.anxinxu.lib.reflections.type.constructor.RefConstructor;
import com.anxinxu.lib.reflections.type.field.RefInt;
import com.anxinxu.lib.reflections.type.field.s.RefStaticLong;
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject;
import com.anxinxu.lib.reflections.type.method.RefMethod;
import com.anxinxu.lib.reflections.type.method.RefStaticMethod;

import java.io.ObjectStreamField;

public class StringReflection {

    public static Class<?> TYPE = RefClass.load(StringReflection.class, String.class, true);
    // else can use class name for not public class
    // public static Class<?> TYPE = RefClass.load(StringReflection.class, "java.lang.String", true);

    public static RefStaticLong serialVersionUID;
    public static RefStaticObject<ObjectStreamField[]> serialPersistentFields;

    @MethodParams({String.class, String.class, int.class})
    // else can use class name
    // @MethodReflectParams({"java.lang.String", "java.lang.String", "int"})
    public static RefStaticMethod<Integer> lastIndexOf;

    @MethodParams({String.class, String.class, int.class})
    public static RefStaticMethod<Integer> indexOf;


    public static RefConstructor<String> constructor;
    @MethodParams(String.class)
    public static RefConstructor<String> constructor1;

    public static RefInt hash;

    // java.lang.String.indexOf(java.lang.String, int)
    // for duplication of name
    @ReflectionName("indexOf")
    @MethodParams({String.class, int.class})
    public static RefMethod<Integer> indexOfForObj; // String#indexOf

    //java.lang.String.indexOf(java.lang.String)
    @ReflectionName("indexOf")
    @MethodParams({String.class})
    public static RefMethod<Integer> indexOfForObj1; // String#indexOf

}

class TestCase {

    // for simple use
    public static void main(String[] args) {
        long serialVersionUID = StringReflection.serialVersionUID.get();
        ObjectStreamField[] serialPersistentFields = StringReflection.serialPersistentFields.get();
        String str = StringReflection.constructor1.newInstance("reflection");
        int hash = StringReflection.hash.get(str);
        int index = StringReflection.indexOf.invoke(str, "fle", 0);
        int index1 = StringReflection.indexOfForObj.invoke(str, "fle", 0);
        int index3 = StringReflection.indexOfForObj1.invoke(str, "fle");
    }
}
