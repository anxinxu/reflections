package com.anxinxu.lib.reflections

import com.anxinxu.lib.reflections.type.RefTypeFactory
import com.anxinxu.lib.reflections.type.base.api.IRefType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface IReflectScope {

    val type: Class<*>

}

inline fun <reified T : IRefType> IReflectScope.injectField(
    targetName: String? = null,
    lazyLoadTarget: Boolean = false
): RefFieldLoader<T> {
    return RefFieldLoader(T::class.java, this, targetName, null, lazyLoadTarget)
}

inline fun <reified T : IRefType> IReflectScope.injectMethod(
    targetName: String? = null,
    params: Array<String>? = null,
    lazyLoadTarget: Boolean = false
): RefFieldLoader<T> {
    return RefFieldLoader(T::class.java, this, targetName, ReflectUtil.toClasses(params), lazyLoadTarget)
}

inline fun <reified T : IRefType> IReflectScope.injectMethod(
    targetName: String? = null,
    params: Array<Class<*>>,
    lazyLoadTarget: Boolean = false
): RefFieldLoader<T> {
    return RefFieldLoader(T::class.java, this, targetName, params, lazyLoadTarget)
}

class RefFieldLoader<T : IRefType>(
    private val clazz: Class<T>,
    private val scope: IReflectScope,
    private val targetName: String?,
    private val params: Array<Class<*>>?,
    private val lazyLoadTarget: Boolean,
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

    private fun createType(clazz: Class<T>, property: KProperty<*>, scope: IReflectScope): T {
        return RefTypeFactory.create(
            clazz,
            scope.type,
            targetName ?: property.name,
            scope.type.name,
            params,
            lazyLoadTarget
        )
    }
}
