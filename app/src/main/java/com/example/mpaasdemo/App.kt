package com.example.mpaasdemo

import android.app.Application
import android.content.Context
import android.util.Log
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework
import com.alipay.mobile.nebula.provider.H5AppCenterPresetProvider
import com.alipay.mobile.nebula.util.H5Utils
import com.finogeeks.lib.applet.client.FinAppClient
import com.finogeeks.lib.applet.client.FinAppConfig
import com.finogeeks.lib.applet.interfaces.FinCallback
import com.mpaas.mas.adapter.api.MPLogger
import com.mpaas.nebula.adapter.api.MPNebula
import com.mpaas.nebula.adapter.api.MPTinyHelper
import com.mpaas.tinyappcommonres.TinyAppCenterPresetProvider


lateinit var APP: App

class App: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        if (FinAppClient.isFinAppProcess(this)) return

        // mPaaS 初始化回调设置
        QuinoxlessFramework.setup(this) {

            // 此回调表示 mPaaS 已经初始化完成，mPaaS 相关调用可在这个回调里进行。
            // 初始化小程序公共资源包
            H5Utils.setProvider(
                H5AppCenterPresetProvider::class.java.name,
                TinyAppCenterPresetProvider()
            )

            OpenWxJSApiPlugin.register()
            MPTinyHelper.getInstance().setLoadingViewClass(AppTinyStartupView::class.java)

            MPNebula.setCustomViewProvider(AppH5ViewProvider())

            // 小程序接入真机预览与调试
            MPTinyHelper.getInstance().apply {
                setTinyAppVHost("example.com")
            }
            MPLogger.setUserId("cyrus")
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (FinAppClient.isFinAppProcess(this)) return
        APP = this

        // mPaaS 初始化
        QuinoxlessFramework.init()

        initFinApp()
    }

    // 初始化凡泰小程序框架
    private fun initFinApp() {
        val uiConfig = FinAppConfig.UIConfig().apply {
            capsuleConfig.capsuleWidth = 0f
        }

        val config = FinAppConfig.Builder()
            .setSdkKey(BuildConfig.SDK_KEY)
            .setSdkSecret(BuildConfig.SDK_SECRET)
            .setApiUrl(BuildConfig.API_URL)
            .setApiPrefix(BuildConfig.API_PREFIX)
            .setEncryptionType(FinAppConfig.ENCRYPTION_TYPE_SM)
            .setUiConfig(uiConfig)
            .build()

        FinAppClient.init(this, config, object: FinCallback<Any?> {
            override fun onSuccess(p0: Any?) {
                Log.d(C.Fin, "凡泰小程序初始化成功")
            }

            override fun onError(p0: Int, p1: String?) {
                Log.e(C.Fin, "凡泰小程序初始化失败（$p1）")
            }

            override fun onProgress(p0: Int, p1: String?) {}
        })
    }
}