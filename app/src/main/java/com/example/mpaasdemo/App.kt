package com.example.mpaasdemo

import android.app.Application
import android.content.Context
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework
import com.alipay.mobile.nebula.provider.H5AppCenterPresetProvider
import com.alipay.mobile.nebula.util.H5Utils
import com.mpaas.mas.adapter.api.MPLogger
import com.mpaas.nebula.adapter.api.MPNebula
import com.mpaas.nebula.adapter.api.MPTinyHelper
import com.mpaas.tinyappcommonres.TinyAppCenterPresetProvider


lateinit var APP: App

class App: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

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
        APP = this

        // mPaaS 初始化
        QuinoxlessFramework.init();
    }
}