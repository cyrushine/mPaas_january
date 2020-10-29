package com.example.mpaasdemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alipay.android.phone.scancode.export.ScanCallback
import com.alipay.android.phone.scancode.export.ScanRequest
import com.alipay.android.phone.scancode.export.ScanService
import com.alipay.mobile.framework.LauncherApplicationAgent
import com.mpaas.nebula.adapter.api.MPNebula
import com.mpaas.nebula.adapter.api.MPNebulaOfflineInfo
import com.mpaas.nebula.adapter.api.MPTinyHelper

class MainActivity : AppCompatActivity() {

    private val scanService by lazy {
        LauncherApplicationAgent.getInstance().microApplicationContext
                .findServiceByInterface<ScanService>(ScanService::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tip).text = "1）预置 H5 应用离线包\n" +
                "2）利用 H5 容器的自定义 JSAPI 功能，让 web 可以调用 native 代码\n" +
                "3）完全自定义的导航栏\n" +
                "4）自定义加载动画等"

        findViewById<Button>(R.id.offline).setOnClickListener {
            workerHandler.post {
                MPNebula.loadOfflineNebula(
                    "h5_json.json", MPNebulaOfflineInfo(
                        "0000000000000001_1.0.5.0.amr", "0000000000000001",
                        "1.0.5.0"
                    )
                )
                MPNebula.startApp("0000000000000001")
            }
        }

        findViewById<View>(R.id.scanDebug).setOnClickListener {
            scanPreviewOrDebugQRCode()
        }

        findViewById<View>(R.id.online).setOnClickListener {
            workerHandler.post {
                val appId = "2018080616290001"
                MPNebula.deleteAppInfo(appId)

                val bundle = Bundle()
                bundle.putBoolean("needAnimInTiny", true)
                MPNebula.startApp(appId, bundle)
            }
        }
    }

    private fun scanPreviewOrDebugQRCode() {
        val scanRequest = ScanRequest()
        scanRequest.setScanType(ScanRequest.ScanType.QRCODE)
        scanService.scan(this, scanRequest, ScanCallback { success, result ->
            val uri = result.data
            if (result == null || !success || uri == null) {
                showScanError()
                return@ScanCallback
            }

            // 启动预览或调试小程序，第二个参数为小程序启动参数
            MPTinyHelper.getInstance().launchIdeQRCode(uri, Bundle())
        })
    }

    private fun showScanError() {
        runOnUiThread { Toast.makeText(this, "扫码失败", Toast.LENGTH_SHORT).show() }
    }
}