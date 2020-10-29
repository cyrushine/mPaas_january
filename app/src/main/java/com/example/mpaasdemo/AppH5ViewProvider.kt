package com.example.mpaasdemo

import android.content.Context
import android.view.ViewGroup
import com.alipay.mobile.h5container.api.H5WebContentImpl
import com.alipay.mobile.nebula.provider.H5ViewProvider
import com.alipay.mobile.nebula.view.*

class AppH5ViewProvider: H5ViewProvider {

    override fun createTitleView(p0: Context): H5TitleView {
        return AppTitleView(context = p0)
    }

    override fun createNavMenu(): H5NavMenuView? {
        return null
    }

    override fun createPullHeaderView(p0: Context?, p1: ViewGroup?): H5PullHeaderView? {
        return null
    }

    override fun createWebContentView(p0: Context): H5WebContentView? {
        return AppWebContentView(p0)
    }
}

class AppWebContentView(context: Context) : H5WebContentImpl(context) {

    override fun getH5LoadingView(): H5LoadingView? {
        return null
    }
}