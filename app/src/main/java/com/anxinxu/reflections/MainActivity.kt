package com.anxinxu.reflections

import android.content.Context
import android.os.Bundle
import android.os.IInterface
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.anxinxu.lib.reflection.android.ActivityThreadReflection
import com.anxinxu.lib.reflection.android.ServiceManagerReflection
import com.anxinxu.lib.reflection.android.VMRuntimeReflection
import com.anxinxu.reflections.databinding.ActivityMainBinding
import com.anxinxu.reflections.mirror.com.anxinxu.reflections.test.*
import com.anxinxu.reflections.test.TestJava
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        Log.e(
            "anxintag",
            "=============================================java============================================"
        )

        testJava()

        Log.e(
            "anxintag",
            "======================================kotlin companion object========================================"
        )

        testKotlinCompanionObject()

        Log.e(
            "anxintag",
            "======================================kotlin object========================================"
        )

        testKotlinObject()

        Log.e(
            "anxintag",
            "======================================MyClass().test()========================================"
        )

        MyClass().test()


        Log.e(
            "anxintag",
            "====================================== MyClass.testCompanionObject()========================================"
        )
        MyClass.testCompanionObject()

        Log.e(
            "anxintag",
            "====================================== Test.testObject()========================================"
        )
        Test.testObject()

        Log.e(
            "anxintag",
            "====================================== VMRuntime ========================================"
        )

        Log.e("anxintag", "TYPE:${VMRuntimeReflection.TYPE}")
        val vmRuntime = VMRuntimeReflection.getRuntime.invoke()
        Log.e("anxintag", "vmRuntime:$vmRuntime")
        Log.e("anxintag", "bootClassPath:${VMRuntimeReflection.bootClassPath(vmRuntime)}")
        Log.e("anxintag", "classPath:${VMRuntimeReflection.classPath(vmRuntime)}")
        Log.e("anxintag", "vmVersion:${VMRuntimeReflection.vmVersion(vmRuntime)}")
        Log.e("anxintag", "vmLibrary:${VMRuntimeReflection.vmLibrary(vmRuntime)}")
        Log.e("anxintag", "vmInstructionSet:${VMRuntimeReflection.vmInstructionSet(vmRuntime)}")
        Log.e("anxintag", "is64Bit:${VMRuntimeReflection.is64Bit(vmRuntime)}")
        Log.e("anxintag", "getTargetSdkVersion:${VMRuntimeReflection.getTargetSdkVersion(vmRuntime)}")
        Log.e("anxintag", "setHiddenApiExemptions:${VMRuntimeReflection.setHiddenApiExemptions.target}")

        Log.e(
            "anxintag",
            "====================================== ActivityThread ========================================"
        )

        Log.e("anxintag", "TYPE:${ActivityThreadReflection.TYPE}")
        val activityThread = ActivityThreadReflection.currentActivityThread.invoke()
        Log.e("anxintag", "activityThread:$activityThread")
        Log.e("anxintag", "currentPackageName:${ActivityThreadReflection.currentPackageName.invoke()}")
        Log.e("anxintag", "currentProcessName:${ActivityThreadReflection.currentProcessName.invoke()}")
        Log.e("anxintag", "currentApplication:${ActivityThreadReflection.currentApplication.invoke()}")
        Log.e("anxintag", "getPackageManager:${ActivityThreadReflection.getPackageManager.invoke()}")
        Log.e("anxintag", "mH:${ActivityThreadReflection.mH.get(activityThread)}")
        Log.e("anxintag", "mAppThread:${ActivityThreadReflection.mAppThread.get(activityThread)}")
        Log.e("anxintag", "mActivities:${ActivityThreadReflection.mActivities.get(activityThread)}")
        Log.e("anxintag", "mServices:${ActivityThreadReflection.mServices.get(activityThread)}")
        Log.e("anxintag", "mLocalProviders:${ActivityThreadReflection.mLocalProviders.get(activityThread)}")

        Log.e(
            "anxintag",
            "====================================== ServiceManager ========================================"
        )

        Log.e("anxintag", "TYPE:${ServiceManagerReflection.TYPE}")
        Log.e("anxintag", "sServiceManager:${ServiceManagerReflection.sServiceManager.get()}")
        Log.e("anxintag", "getIServiceManager:${ServiceManagerReflection.getIServiceManager.invoke()}")
        Log.e("anxintag", "sCache:${ServiceManagerReflection.sCache.get()}")
        Log.e(
            "anxintag",
            "getService(${Context.ACTIVITY_SERVICE}):${ServiceManagerReflection.getService.invoke(Context.ACTIVITY_SERVICE)}"
        )

        Log.e(
            "anxintag",
            "====================================== hookBinder ========================================"
        )
        ServiceManagerReflection.hookBinder(
            "webviewupdate",
            "android.webkit.IWebViewUpdateService",
            object : ServiceManagerReflection.ServiceCall {

                override fun beforeCall(originService: IInterface, method: Method, args: Array<out Any>?): Boolean {
                    super.beforeCall(originService, method, args)
                    return method.name == "waitForAndGetProvider"
                }

                override fun afterCall(
                    originService: IInterface,
                    method: Method,
                    args: Array<Any>?,
                    originReturn: Any?
                ): ServiceManagerReflection.ServiceCall.Result {
                    return if (method.name == "waitForAndGetProvider") {
                        Log.d("anxintag", "MainActivity args:$args")
                        super.afterCall(originService, method, args, originReturn)
                        val packageInfo = WebViewProviderResponseReflection.packageInfo.get(originReturn)
                        val status = WebViewProviderResponseReflection.status.get(originReturn)
                        Log.e("anxintag", "originReturn:$originReturn, status:$status, packageInfo:$packageInfo")
                        ServiceManagerReflection.ServiceCall.Result(
                            true, WebViewProviderResponseReflection.constructor.newInstance(packageInfo, status)
                        )
                    } else {
                        super.afterCall(originService, method, args, originReturn)
                    }
                }
            })

        WebView(this)
    }

    private fun testJava() {
        TestJava.setsCurrentActivity(this)
        TestJava.setsMsg("test static string")
        TestJava.setsStatus(3)
        TestJava.setsTestBoolean(true)
        Log.e("anxintag", "static toString:" + TestJava.tosString())

        val testJava = TestJava()
        testJava.isTestBoolean = true
        testJava.lock = Any()
        testJava.msg = "object string"
        Log.e("anxintag", "object toString$testJava")

        Log.e(
            "anxintag",
            "get static sCurrentActivity : ${TestJavaRef.sCurrentActivity.get()}"
        )
        Log.e("anxintag", "get static a：${TestJavaRef.a.get()}")

        Log.e(
            "anxintag",
            "get static sMsg :${TestJavaRef.sMsg.get()}"
        )

        Log.e("anxintag", "set static sCurrentActivity null")
        TestJavaRef.sCurrentActivity.set(null)
        Log.e("anxintag", "get static sCurrentActivity：${TestJavaRef.sCurrentActivity.get()}")
        Log.e("anxintag", "get static a：${TestJavaRef.a.get()}")

        Log.e("anxintag", "set static sMsg okkk")
        TestJavaRef.sMsg.set("okkk")
        Log.e("anxintag", "get static sMsg : ${TestJavaRef.sMsg.get()}")

        Log.e("anxintag", "get object lock :${TestJavaRef.lock.get(testJava)}")
        Log.e("anxintag", "get object testBoolean :${TestJavaRef.testBoolean.get(testJava)}")

        Log.e("anxinTag", "set object lock String(\"string lock\")")
        TestJavaRef.lock.set(testJava, "string lock")
        Log.e("anxintag", "get object lock :${TestJavaRef.lock.get(testJava)}")

        Log.e("anxinTag", "set object testBoolean false")
        TestJavaRef.testBoolean.set(testJava, false)
        Log.e("anxintag", "get object testBoolean :${TestJavaRef.testBoolean.get(testJava)}")

        Log.e(
            "anxintag",
            "constructor newInstance:${TestJavaRef.constructor.newInstance("test constructor")}, ${TestJavaRef.constructor.error}"
        )
        Log.e("anxintag", "invoke static method testsToString ${TestJavaRef.testsToString.invoke()}")

        Log.e(
            "anxintag",
            "invoke static method setActivityAndLog ${TestJavaRef.setActivityAndLog.invoke("testetsts", Any(), this)}"
        )
        Log.e("anxintag", "invoke static method testsToString ${TestJavaRef.testsToString.invoke()}")
        Log.e(
            "anxintag",
            "invoke static method testStaticMethod ${TestJavaRef.testStaticMethod.invoke("testStaticMethod", testJava)}"
        )
        Log.e(
            "anxintag",
            "invoke static method abc ${TestJavaRef.abc.invoke(testJava)}"
        )

        Log.e(
            "anxintag",
            "invoke static method abc ${TestJava.abc(testJava)}"
        )
    }

    private fun testKotlinObject() {
        TestJava.setsCurrentActivity(this)
        TestJava.setsMsg("test kotlin static string")
        TestJava.setsStatus(5)
        TestJava.setsTestBoolean(true)
        Log.e("anxintag", "static kotlin toString:" + TestJava.tosString())

        val testJava = TestJava()
        testJava.isTestBoolean = true
        testJava.lock = Any()
        testJava.msg = "object kotlin string"
        Log.e("anxintag", "object kotlin toString$testJava")

        Log.e(
            "anxintag",
            "get kotlin static sCurrentActivity : ${TestJavaRefObjectKts.sCurrentActivity?.get()}"
        )
        Log.e("anxintag", "get static a：${TestJavaRefObjectKts.a?.get()}")

        Log.e(
            "anxintag",
            "get static sMsg :${TestJavaRefObjectKts.sMsg?.get()}"
        )

        Log.e("anxintag", "set static sCurrentActivity null")
        TestJavaRefObjectKts.sCurrentActivity?.set(null)
        Log.e("anxintag", "get static sCurrentActivity：${TestJavaRefObjectKts.sCurrentActivity?.get()}")
        Log.e("anxintag", "get static a：${TestJavaRefObjectKts.a?.get()}")

        Log.e("anxintag", "set static sMsg okkk")
        TestJavaRefObjectKts.sMsg?.set("okkk")
        Log.e("anxintag", "get static sMsg : ${TestJavaRefObjectKts.sMsg?.get()}")

        Log.e("anxintag", "get object lock :${TestJavaRefObjectKts.lock?.get(testJava)}")
        Log.e("anxintag", "get object testBoolean :${TestJavaRefObjectKts.testBoolean?.get(testJava)}")

        Log.e("anxinTag", "set object lock String(\"string lock\")")
        TestJavaRefObjectKts.lock?.set(testJava, "string lock")
        Log.e("anxintag", "get object lock :${TestJavaRefObjectKts.lock?.get(testJava)}")

        Log.e("anxinTag", "set object testBoolean false")
        TestJavaRefObjectKts.testBoolean?.set(testJava, false)
        Log.e("anxintag", "get object testBoolean :${TestJavaRefObjectKts.testBoolean?.get(testJava)}")

        Log.e(
            "anxintag",
            "constructor newInstance:${TestJavaRefObjectKts.constructor?.newInstance("test constructor")}, ${TestJavaRefObjectKts.constructor?.error}"
        )
        Log.e("anxintag", "invoke static method testsToString ${TestJavaRefObjectKts.testsToString?.invoke()}")

        Log.e(
            "anxintag",
            "invoke static method setActivityAndLog ${
                TestJavaRefObjectKts.setActivityAndLog?.invoke(
                    "testetsts",
                    Any(),
                    this
                )
            }"
        )
        Log.e("anxintag", "invoke static method testsToString ${TestJavaRefObjectKts.testsToString?.invoke()}")
        Log.e(
            "anxintag",
            "invoke static method testStaticMethod ${
                TestJavaRefObjectKts.testStaticMethod?.invoke(
                    "testStaticMethod",
                    testJava
                )
            }"
        )
        Log.e(
            "anxintag",
            "invoke static method abc ${TestJavaRefObjectKts.abc?.invoke(testJava)}"
        )
    }

    private fun testKotlinCompanionObject() {

        TestJava.setsCurrentActivity(this)
        TestJava.setsMsg("test kotlin static string")
        TestJava.setsStatus(5)
        TestJava.setsTestBoolean(true)
        Log.e("anxintag", "static kotlin toString:" + TestJava.tosString())

        val testJava = TestJava()
        testJava.isTestBoolean = true
        testJava.lock = Any()
        testJava.msg = "object kotlin string"
        Log.e("anxintag", "object kotlin toString$testJava")

        Log.e(
            "anxintag",
            "get kotlin static sCurrentActivity : ${TestJavaRefKts.sCurrentActivity?.get()}"
        )
        Log.e("anxintag", "get static a：${TestJavaRefKts.a?.get()}")

        Log.e(
            "anxintag",
            "get static sMsg :${TestJavaRefKts.sMsg?.get()}"
        )

        Log.e("anxintag", "set static sCurrentActivity null")
        TestJavaRefKts.sCurrentActivity?.set(null)
        Log.e("anxintag", "get static sCurrentActivity：${TestJavaRefKts.sCurrentActivity?.get()}")
        Log.e("anxintag", "get static a：${TestJavaRefKts.a?.get()}")

        Log.e("anxintag", "set static sMsg okkk")
        TestJavaRefKts.sMsg?.set("okkk")
        Log.e("anxintag", "get static sMsg : ${TestJavaRefKts.sMsg?.get()}")

        Log.e("anxintag", "get object lock :${TestJavaRefKts.lock?.get(testJava)}")
        Log.e("anxintag", "get object testBoolean :${TestJavaRefKts.testBoolean?.get(testJava)}")

        Log.e("anxinTag", "set object lock String(\"string lock\")")
        TestJavaRefKts.lock?.set(testJava, "string lock")
        Log.e("anxintag", "get object lock :${TestJavaRefKts.lock?.get(testJava)}")

        Log.e("anxinTag", "set object testBoolean false")
        TestJavaRefKts.testBoolean?.set(testJava, false)
        Log.e("anxintag", "get object testBoolean :${TestJavaRefKts.testBoolean?.get(testJava)}")

        Log.e(
            "anxintag",
            "constructor newInstance:${TestJavaRefKts.constructor?.newInstance("test constructor")}, ${TestJavaRefKts.constructor?.error}"
        )
        Log.e("anxintag", "invoke static method testsToString ${TestJavaRefKts.testsToString?.invoke()}")

        Log.e(
            "anxintag",
            "invoke static method setActivityAndLog ${
                TestJavaRefKts.setActivityAndLog?.invoke(
                    "testetsts",
                    Any(),
                    this
                )
            }"
        )
        Log.e("anxintag", "invoke static method testsToString ${TestJavaRefKts.testsToString?.invoke()}")
        Log.e(
            "anxintag",
            "invoke static method testStaticMethod ${
                TestJavaRefKts.testStaticMethod?.invoke(
                    "testStaticMethod",
                    testJava
                )
            }"
        )
        Log.e(
            "anxintag",
            "invoke static method abc ${TestJavaRefKts.abc?.invoke(testJava)}"
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        if (navController.currentDestination?.id == R.id.FirstFragment) {
            finish()
            return
        }
        super.onBackPressed()
        Log.e("anxintag", "onBackPressed")
    }

    override fun finish() {
        super.finish()
        Log.e("anxintag", "finish")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}