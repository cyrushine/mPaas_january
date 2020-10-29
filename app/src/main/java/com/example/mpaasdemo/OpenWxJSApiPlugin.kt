package com.example.mpaasdemo

import android.content.Intent
import android.util.Log
import com.alipay.mobile.h5container.api.*
import com.mpaas.nebula.adapter.api.MPNebula
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 自定义 JSAPI，打开微信
 */
class OpenWxJSApiPlugin: H5SimplePlugin() {

    companion object {
        private const val TAG = "OpenWxJSApiPlugin"
        private const val FUNC_NAME = "openWX"
        private const val WX_PKG = "com.tencent.mm"

        private val REGISTED = AtomicBoolean(false)

        public fun register() {
            if (REGISTED.compareAndSet(false, true)) {
                /*
                * 第一个参数，自定义 API 类的全路径
                * 第二个参数，BundleName，aar/inside可以直接填 ""
                * 第三个参数，作用于，可以直接填 "page"
                * 第四个参数，作用的 API，将你自定义的 API 以 String[] 的形式传入
                */
                MPNebula.registerH5Plugin(OpenWxJSApiPlugin::class.java.name, null,
                        "page", arrayOf(FUNC_NAME))
            }
        }
    }

    private val ctx by lazy { APP }
    private val pm by lazy { ctx.packageManager }

    override fun onInitialize(coreNode: H5CoreNode?) {
        super.onInitialize(coreNode)
        Log.d(TAG, "onInitialize")
    }

    override fun onRelease() {
        super.onRelease()
        Log.d(TAG, "onRelease")
    }

    override fun interceptEvent(event: H5Event?, context: H5BridgeContext?): Boolean {
        Log.d(TAG, "interceptEvent")
        return super.interceptEvent(event, context)
    }

    override fun onPrepare(filter: H5EventFilter) {
        super.onPrepare(filter)
        filter.addAction(FUNC_NAME)
        Log.d(TAG, "onPrepare")
    }

    override fun handleEvent(event: H5Event, context: H5BridgeContext): Boolean {
        Log.d(TAG, "handleEvent")
        when (event.action) {
            FUNC_NAME -> {
                val intent = pm.getLaunchIntentForPackage(WX_PKG) ?: return false
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ctx.startActivity(intent)
                context.sendSuccess()
                return true
            }
        }
        return false
    }
}