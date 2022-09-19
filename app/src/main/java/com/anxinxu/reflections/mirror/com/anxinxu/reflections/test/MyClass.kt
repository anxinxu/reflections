package com.anxinxu.reflections.mirror.com.anxinxu.reflections.test

import com.anxinxu.lib.reflections.IReflectScope
import com.anxinxu.lib.reflections.injectField
import com.anxinxu.lib.reflections.injectMethod
import com.anxinxu.lib.reflections.type.constructor.RefConstructor
import com.anxinxu.lib.reflections.type.field.RefObject
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject
import com.anxinxu.lib.reflections.type.method.RefMethod
import com.anxinxu.lib.reflections.type.method.RefStaticMethod

private fun log(msg: String?) {
    println("anxintag log  $msg")
}

class MyClass : IReflectScope {

    override val type: Class<*> by lazy { com.anxinxu.reflections.test.TestJavaClass::class.java }

    companion object : IReflectScope {
        override val type: Class<*> by lazy { com.anxinxu.reflections.test.TestJavaClass::class.java }
        val abc: RefStaticObject<String?> by injectField()
        val objAbc: RefObject<String> by injectField()

        val test: RefStaticMethod<Void> by injectMethod()

        val test1: RefStaticMethod<Void> by injectMethod(params = arrayOf(String::class.java))
        val testObjMethod: RefMethod<Void> by injectMethod()
        val test11111: RefStaticMethod<Void> by injectMethod(targetName = "test1", params = arrayOf(String::class.java))
        val constructor: RefConstructor<com.anxinxu.reflections.test.TestJavaClass> by injectMethod()
        val constructor1: RefConstructor<com.anxinxu.reflections.test.TestJavaClass> by injectMethod(params = arrayOf("java.lang.String"))

        fun testCompanionObject() {
            val testJavaClass = com.anxinxu.reflections.test.TestJavaClass()
            abc.set("testCompanionObject")
            val objAbc = objAbc.get(testJavaClass)
            log(objAbc)
            log(abc.get())
            abc.set(null)
            log(abc.get())

            test.invoke()
            test1.invoke("aaaaaaaa")
            test11111.invoke("aaaaaaaa test11111")
            testObjMethod.invoke(testJavaClass)

            log(constructor.newInstance().objAbc)
            log(constructor1.newInstance("ojbk").objAbc)
        }
    }

    val abc: RefStaticObject<String?> by injectField()
    val objAbc: RefObject<String> by injectField()

    val test: RefStaticMethod<Void> by injectMethod()

    val test1: RefStaticMethod<Void> by injectMethod(params = arrayOf(String::class.java))
    val testObjMethod: RefMethod<Void> by injectMethod()
    val test11111: RefStaticMethod<Void> by injectMethod(targetName = "test1", params = arrayOf(String::class.java))
    val constructor: RefConstructor<com.anxinxu.reflections.test.TestJavaClass> by injectMethod()
    val constructor1: RefConstructor<com.anxinxu.reflections.test.TestJavaClass> by injectMethod(params = arrayOf("java.lang.String"))

    private fun log1(msg: String?) {
        println("anxintag log1 $msg")
    }

    fun test() {
        val testJavaClass = com.anxinxu.reflections.test.TestJavaClass()
        abc.set("test")
        val objAbc = objAbc.get(testJavaClass)
        log1(objAbc)
        log1(abc.get())
        abc.set(null)
        log1(abc.get())

        test.invoke()
        test1.invoke("aaaaaaaa")
        test11111.invoke("aaaaaaaa test11111")
        testObjMethod.invoke(testJavaClass)

        log(constructor.newInstance().objAbc)
        log(constructor1.newInstance("ojbk").objAbc)
    }


}

object Test : IReflectScope {
    override val type: Class<*> by lazy { com.anxinxu.reflections.test.TestJavaClass::class.java }

    val abc: RefStaticObject<String?> by injectField()
    val objAbc: RefObject<String> by injectField()

    val test: RefStaticMethod<Void> by injectMethod()

    val test1: RefStaticMethod<Void> by injectMethod(params = arrayOf(String::class.java))
    val testObjMethod: RefMethod<Void> by injectMethod()
    val test11111: RefStaticMethod<Void> by injectMethod(targetName = "test1", params = arrayOf(String::class.java))
    val constructor: RefConstructor<com.anxinxu.reflections.test.TestJavaClass> by injectMethod()
    val constructor1: RefConstructor<com.anxinxu.reflections.test.TestJavaClass> by injectMethod(params = arrayOf("java.lang.String"))

    private fun log1(msg: String?) {
        println("anxintag log2 $msg")
    }

    fun testObject() {
        val testJavaClass = com.anxinxu.reflections.test.TestJavaClass()
        abc.set("testObject")
        val objAbc = objAbc.get(testJavaClass)
        log1(objAbc)
        log1(abc.get())
        abc.set(null)
        log1(abc.get())

        test.invoke()
        test1.invoke("aaaaaaaa")
        test11111.invoke("aaaaaaaa test11111")
        testObjMethod.invoke(testJavaClass)

        log(constructor.newInstance().objAbc)
        log(constructor1.newInstance("ojbk").objAbc)
    }
}

