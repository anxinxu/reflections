package com.anxinxu.reflections.mirror.com.anxinxu.reflections.test

import com.anxinxu.lib.reflections.IReflectScope
import com.anxinxu.lib.reflections.injectField
import com.anxinxu.lib.reflections.injectMethod
import com.anxinxu.lib.reflections.type.constructor.RefConstructor
import com.anxinxu.lib.reflections.type.field.RefInt
import com.anxinxu.lib.reflections.type.field.s.RefStaticLong
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject
import com.anxinxu.lib.reflections.type.method.RefMethod
import com.anxinxu.lib.reflections.type.method.RefStaticMethod
import java.io.ObjectStreamField


object StringReflectionKtx : IReflectScope {
    override val type: Class<*> = String::class.java
    // else
    // override val type: Class<*> by lazy { Class.forName("java.lang.String") }

    val serialVersionUID: RefStaticLong by injectField()
    val serialPersistentFields: RefStaticObject<Array<ObjectStreamField>> by injectField()

    // java.lang.String#lastIndexOf(java.lang.String, java.lang.String, int)
    val lastIndexOf: RefStaticMethod<Int> by injectMethod(params = arrayOf(String::class.java, String::class.java, Integer.TYPE))
    // else
    // val lastIndexOf: RefStaticMethod<Int> by injectMethod(params = arrayOf("java.lang.String", "java.lang.String", "int"))
    val indexOf: RefStaticMethod<Int> by injectMethod(params = arrayOf(String::class.java, String::class.java, Integer.TYPE))

    val constructor: RefConstructor<String> by injectMethod()
    val constructor1: RefConstructor<String> by injectMethod(params = arrayOf(String::class.java))

    val hash: RefInt by injectField()

    // java.lang.String.indexOf(java.lang.String, int)
    // for duplication of name
    val indexOfForObj: RefMethod<Int> by injectMethod(targetName = "indexOf", params = arrayOf(String::class.java, Integer.TYPE))

    //java.lang.String.indexOf(java.lang.String)
    val indexOfForObj1: RefMethod<Int> by injectMethod(targetName = "indexOf", params = arrayOf(String::class.java))
}

class TestCaseKtx {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val serialVersionUID = StringReflectionKtx.serialVersionUID.get()
            val serialPersistentFields = StringReflectionKtx.serialPersistentFields.get()
            val str = StringReflectionKtx.constructor1.newInstance("reflection")
            val hash = StringReflectionKtx.hash.get(str)
            val index = StringReflectionKtx.indexOf(str, "fle", 0)
            val index1 = StringReflectionKtx.indexOfForObj(str, "fle", 0)
            val index3 = StringReflectionKtx.indexOfForObj1(str, "fle")
        }
    }
}