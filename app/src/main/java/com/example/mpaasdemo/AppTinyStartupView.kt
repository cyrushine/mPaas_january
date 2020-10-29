package com.example.mpaasdemo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.mpaas.nebula.adapter.api.MPTinyBaseIntermediateLoadingView

/**
 * 自定义小程序容器的启动加载页
 */
class AppTinyStartupView: MPTinyBaseIntermediateLoadingView {

    private val layoutInflater by lazy {
        LayoutInflater.from(context)
    }

    private val tip: TextView

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        layoutInflater.inflate(R.layout.app_tiny_startup_view, this, true)
        tip = findViewById(R.id.startUpTip)
        tip.text = "自定义启动加载页"
    }

    override fun initView(info: AppInfo?) {}

    override fun onError() {}

    override fun update(info: AppInfo?) {}



}