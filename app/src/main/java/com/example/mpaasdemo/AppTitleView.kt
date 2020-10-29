package com.example.mpaasdemo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alibaba.fastjson.JSONObject
import com.alipay.mobile.nebula.view.AbsTitleView
import com.alipay.mobile.nebula.view.IH5TinyPopMenu
import com.mpaas.nebula.adapter.view.MpaasDefaultH5TitleView

/**
 * 自定义导航栏
 */
class AppTitleView(context: Context) : MpaasDefaultH5TitleView(context) {

    init {
        super.showCloseButton(false)
        super.showBackButton(false)
        subTitleView.visibility = View.GONE

    }

    override fun setOptionMenu(p0: String?, p1: JSONObject?) {

    }

    override fun getBackgroundColor(): Int {
        return Color.WHITE
    }

    override fun setBackgroundAlphaValue(p0: Int) {

    }

    override fun setBackgroundColor(p0: Int) {
        contentView.setBackgroundColor(Color.WHITE)
    }

    override fun setTitleColorWhiteTheme() {

    }

    override fun setTitleColorBlueTheme() {

    }

    override fun setIconWhiteTheme() {

    }

    override fun setIconBlueTheme() {

    }

    override fun showBackHome(p0: Boolean) {

    }

    override fun setTitle(s: String?) {
        super.setTitle("完全自定义的导航栏")
    }

    override fun showCloseButton(b: Boolean) {}

    override fun showBackButton(b: Boolean) {}

    override fun getOptionMenuContainer(): View? {
        return null
    }

    override fun getOptionMenuContainer(index: Int): View? {
        return null
    }

    override fun getH5TinyPopMenu(): IH5TinyPopMenu {
        return super.getH5TinyPopMenu()
    }
}