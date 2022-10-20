# reflections
[![](https://jitpack.io/v/anxinxu/reflections.svg)](https://jitpack.io/#anxinxu/reflections)

this projec only simple use relection.

## Download

You can download jar/aar from GitHub's [releases page][1].


Or use Gradle:
### Step 1. Add the JitPack repository to your build file
```gradle
repositories {
    // add this
    maven { url 'https://jitpack.io' }
}
```

### Step 2. Add the dependency
```gradle
dependencies {
  // for java
  implementation 'com.github.anxinxu:reflections:2.4'
  // for android
  implementation 'com.github.anxinxu.reflections:Reflections-android:2.4'
  // for kotlin
  implementation 'com.github.anxinxu.reflections:Reflections-ktx:2.4'
}
```

## How do I use Reflections?
Simple use cases will look something like this:

### java
```java
// for java simple
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
```

### kotlin
```kotlin

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
```

### Android
```java
// eg.
Object activityThread = ActivityThreadReflection.currentActivityThread.invoke();
Application application = ActivityThreadReflection.currentApplication.invoke();
String packageName = ActivityThreadReflection.currentPackageName.invoke();
String processName = ActivityThreadReflection.currentProcessName.invoke();
Handler mH = ActivityThreadReflection.mH.get(activityThread);
```

## Other 
in android i need add this in your file `proguard-rules.pro`
```java
-keep class xxx.xxx.xxx.StringReflection{*;}
-keep class xxx.xxx.xxx.StringReflectionKtx{*;}
```

if i use `reflections:Reflections-android` you need add this
```java
-keep class com.anxinxu.lib.reflection.android.ActivityThreadReflection{*;}
-keep class com.anxinxu.lib.reflection.android.ServiceManagerReflection{*;}
-keep class com.anxinxu.lib.reflection.android.VMRuntimeReflection{*;}
```

## Thanks

[1]: https://github.com/anxinxu/reflections/releases
