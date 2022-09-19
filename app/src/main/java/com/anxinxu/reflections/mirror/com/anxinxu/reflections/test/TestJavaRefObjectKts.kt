package com.anxinxu.reflections.mirror.com.anxinxu.reflections.test

import android.app.Activity
import com.anxinxu.lib.reflections.MethodParams
import com.anxinxu.lib.reflections.MethodReflectParams
import com.anxinxu.lib.reflections.RefClass
import com.anxinxu.lib.reflections.ReflectionName
import com.anxinxu.lib.reflections.type.constructor.RefConstructor
import com.anxinxu.lib.reflections.type.field.RefBoolean
import com.anxinxu.lib.reflections.type.field.RefInt
import com.anxinxu.lib.reflections.type.field.RefObject
import com.anxinxu.lib.reflections.type.field.s.RefStaticBoolean
import com.anxinxu.lib.reflections.type.field.s.RefStaticInt
import com.anxinxu.lib.reflections.type.field.s.RefStaticObject
import com.anxinxu.lib.reflections.type.method.RefStaticMethod
import com.anxinxu.reflections.test.TestJava

object TestJavaRefObjectKts {

    val TYPE = RefClass.load(TestJavaRefObjectKts::class.java, this, TestJava::class.java, true)

    fun a() {
    }

    @ReflectionName("sCurrentActivity")
    var a: RefStaticObject<Any>? = null
    var sCurrentActivity: RefStaticObject<Activity>? = null
    var sStatus: RefStaticInt? = null
    var sTestBoolean: RefStaticBoolean? = null
    var sMsg: RefStaticObject<String>? = null

    var lock: RefObject<Any>? = null
    var status: RefInt? = null
    var testBoolean: RefBoolean? = null
    var msg: RefObject<String>? = null

    @MethodParams(String::class)
    var constructor: RefConstructor<TestJava>? = null

    var testsToString: RefStaticMethod<String>? = null

    @MethodParams(String::class, Any::class)
    var testStaticMethod: RefStaticMethod<Void>? = null

    @MethodParams(String::class, Any::class, Activity::class)
    @ReflectionName("a")
    var setActivityAndLog: RefStaticMethod<Void>? = null

    @MethodReflectParams("com.anxinxu.reflections.test.TestJava")
    var abc: RefStaticMethod<Void>? = null

}