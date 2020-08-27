package com.seatrend.lockerapp.ui

import RF610_Package.RF610_Serial
import android.annotation.SuppressLint
import com.seatrend.lockerapp.MainActivity
import com.seatrend.lockerapp.R
import com.seatrend.lockerapp.base.Constants
import com.seatrend.lockerapp.base.BaseActivity
import com.seatrend.utilsdk.thread.ThreadPoolManager
import com.seatrend.utilsdk.time.TimeCounter
import kotlinx.android.synthetic.main.activity_identity.*

/**
 * Created by ly on 2020/8/11 14:01
 */
class IdentityActivity : BaseActivity(), TimeCounter.TimeCallBack {

    private var activityOut: Boolean = false //activity是否销毁或隐藏
    private var isReady: Boolean = false //设备连接
    val PORT = "4"  //端口
    val BTL = 9600 //波特率
    val  PERIO :Long = 2000 //间隔多少秒去读

    override fun initView() {
        setPageTitle(resources.getString(R.string.app_name))
        ThreadPoolManager.getInstance().execute { connectionDevice() }
    }

    @Synchronized
    private fun readCard() {
        showLog("readCard")
        if (isReady && !activityOut) {
            showLog("读卡一次")
            var re = 0
            val _Version = ByteArray(20)
            re = RF610_Serial.RF610_S50GetCardID(_Version)
            if (re == 0) {
                showLog(
                    "S50获取卡号成功，卡号：" + Integer.toHexString(_Version[0].toInt() and 0xFF).toUpperCase() + Integer.toHexString(
                        _Version[1].toInt() and 0xFF
                    ).toUpperCase() + Integer.toHexString(_Version[2].toInt() and 0xFF).toUpperCase() + Integer.toHexString(
                        _Version[3].toInt() and 0xFF
                    ).toUpperCase()
                )

//                ToastUtil.show(this,"读卡成功")
                if (Constants.SAVE == intent.getStringExtra(Constants.FS)) {
                    activityOut =true
                    intent.setClass(this, HphmActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    activityOut =true
                    intent.setClass(this, TakeOutActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else
                showLog("S50获取卡号失败，错误代码为：" + RF610_Serial.ErrorCode(re, 0))
        }
    }

    private fun connectionDevice() {
        val StrPort = "/dev/ttyS$PORT"
        var re = 1
        val Baudate = BTL
        re = RF610_Serial.RF610_CommOpenWithBaud(StrPort, Baudate)
        if (re == 0) {
            re = RF610_Serial.RF610_Reset()
            if (re == 0) {
                isReady = true
            } else {
                showErrorDialog("设备连接失败，错误代码为：" + RF610_Serial.ErrorCode(re, 0))
                RF610_Serial.RF610_CommClose()
            }
        } else {
            showErrorDialog("串口打开错误，错误代码为：" + RF610_Serial.ErrorCode(re, 0))
        }

    }

    override fun onResume() {
        super.onResume()
        activityOut = false
        TimeCounter.getInstance().cancel()
        TimeCounter.getInstance().run(this)

        ThreadPoolManager.getInstance().createSchedulePool({
            readCard()
        },0,PERIO)
    }

    override fun onPause() {
        super.onPause()
        activityOut = true
        TimeCounter.getInstance().cancel()

        ThreadPoolManager.getInstance().cancel()
    }

    @SuppressLint("SetTextI18n")
    override fun countTimeBack(thisTime: Long) {
        runOnUiThread {
            timetv!!.text = getString(R.string.time_limmit, thisTime / 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityOut = true
        isReady = false
    }

    override fun TimeOutBack() {
        startActivityToTop(MainActivity::class.java)
    }

    override fun bindEvent() {
        identity.setOnClickListener {
            if (Constants.SAVE == intent.getStringExtra(Constants.FS)) {
                activityOut =true
                intent.setClass(this, HphmActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                activityOut =true
                intent.setClass(this, TakeOutActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_identity
    }
}