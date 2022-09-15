package com.anxinxu.lib.reflections

import com.anxinxu.lib.reflections.type.RefTypeFactory
import com.anxinxu.lib.reflections.type.base.api.IRefType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface ReflectScope {

    val type: Class<*>

}

inline fun <reified T : IRefType> ReflectScope.injectField(targetName: String? = null): RefFieldLoader<T> {
    return RefFieldLoader(T::class.java, this, targetName, null)
}

inline fun <reified T : IRefType> ReflectScope.injectMethod(
    targetName: String? = null,
    params: Array<String>? = null
): RefFieldLoader<T> {
    return RefFieldLoader(T::class.java, this, targetName, ReflectUtil.toClasses(params))
}

inline fun <reified T : IRefType> ReflectScope.injectMethod(
    targetName: String? = null,
    params: Array<Class<*>>
): RefFieldLoader<T> {
    return RefFieldLoader(T::class.java, this, targetName, params)
}

class RefFieldLoader<T : IRefType>(
    private val clazz: Class<T>,
    private val scope: ReflectScope,
    private val targetName: String?,
    private val params: Array<Class<*>>?
) {
    private lateinit var prop: ReadOnlyProperty<Any, T>

    operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadOnlyProperty<Any, T> {
        if (this::prop.isInitialized) {
            return prop
        }
        synchronized(this) {
            if (this::prop.isInitialized) {
                return prop
            }
            val refType = createType(clazz, property, scope)
            prop = ReadOnlyProperty { _, _ -> refType }
        }
        return prop
    }

    private fun createType(clazz: Class<T>, property: KProperty<*>, scope: ReflectScope): T {
        return RefTypeFactory.create(
            clazz,
            scope.type,
            targetName ?: property.name,
            scope.type.name,
            params
        )
    }
}
