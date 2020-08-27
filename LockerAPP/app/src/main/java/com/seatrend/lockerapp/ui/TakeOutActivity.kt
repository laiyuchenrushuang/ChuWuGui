package com.seatrend.lockerapp.ui

import android.annotation.SuppressLint
import com.seatrend.lockerapp.MainActivity
import com.seatrend.lockerapp.R
import com.seatrend.lockerapp.base.BaseActivity
import com.seatrend.utilsdk.time.TimeCounter
import com.seatrend.utilsdk.utils.CarHphmUtils
import kotlinx.android.synthetic.main.activity_takeout.*

/**
 * Created by ly on 2020/8/12 11:28
 */
class TakeOutActivity: BaseActivity(), TimeCounter.TimeCallBack {
    override fun initView() {
        code.transformationMethod = CarHphmUtils.TransInformation()
    }

    override fun bindEvent() {
        btn_next.setOnClickListener {
            startActivityToTop(MainActivity::class.java)
        }
    }
    override fun onResume() {
        super.onResume()
        TimeCounter.getInstance().cancel()
        TimeCounter.getInstance().run(this)
    }

    override fun onPause() {
        super.onPause()
        TimeCounter.getInstance().cancel()
    }

    @SuppressLint("SetTextI18n")
    override fun countTimeBack(thisTime: Long) {
        runOnUiThread {
            timetv!!.text = getString(R.string.time_limmit, thisTime / 1000)
        }
    }

    override fun TimeOutBack() {
        startActivityToTop(MainActivity::class.java)
    }
    override fun getLayout(): Int {
        return R.layout.activity_takeout
    }
}