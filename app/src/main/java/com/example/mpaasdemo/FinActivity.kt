package com.example.mpaasdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.finogeeks.lib.applet.client.FinAppClient
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files

/**
 * 凡泰小程序测试页
 */
class FinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin)

        findViewById<Button>(R.id.openApplet).setOnClickListener {
            FinAppClient.appletApiManager.startApplet(this, "600e62d8452cff0001d13781")
        }

        findViewById<Button>(R.id.openOfflineApplet).setOnClickListener {
            workerHandler.post {
                startOfflineApplet()
            }
        }
        findViewById<TextView>(R.id.openAppletTip).text = "1）没法预置小程序\n" +
                "2）可以网页/小程序调用原生 API\n" +
                "3）可以从 native 调用小程序函数/发送事件\n" +
                "4）可以隐藏小程序右上角的操作按钮\n" +
                "5）没法自定义加载小程序的样式"
    }

    /**
     * 启动预置的小程序
     */
    private fun startOfflineApplet() {
        try {
            FinAppClient.appletApiManager.clearApplets()
            val appletDir = File(filesDir, "finapplet/api.finclip.com/archives/app")
            appletDir.mkdirs()
            val appletZipName = "600e62d8452cff0001d13781-1.0.0-1.zip"
            val appletZip = File(appletDir, appletZipName)

            if (!appletZip.exists()) {
                assets.open("fin/$appletZipName").copyTo(appletZip)
            }

            FinAppClient.appletApiManager.startApplet(this, "600e62d8452cff0001d13781")
        } catch (e: Exception) {
            Log.e(C.Fin, e.message, e)
        }
    }
}