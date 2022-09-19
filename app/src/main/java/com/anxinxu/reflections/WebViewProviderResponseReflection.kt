package com.anxinxu.reflections

import com.anxinxu.lib.reflections.IReflectScope
import com.anxinxu.lib.reflections.injectField
import com.anxinxu.lib.reflections.injectMethod
import com.anxinxu.lib.reflections.type.constructor.RefConstructor
import com.anxinxu.lib.reflections.type.field.RefInt
import com.anxinxu.lib.reflections.type.field.RefObject

object WebViewProviderResponseReflection : IReflectScope {
    override val type: Class<*> = Class.forName("android.webkit.WebViewProviderResponse")

    val packageInfo: RefObject<Any> by injectField()
    val status: RefInt by injectField()

    val constructor: RefConstructor<Any> by injectMethod(params = arrayOf("android.content.pm.PackageInfo", "int"))
}