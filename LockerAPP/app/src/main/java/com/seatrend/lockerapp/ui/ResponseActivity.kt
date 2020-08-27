package com.seatrend.lockerapp.ui

import android.annotation.SuppressLint
import com.seatrend.lockerapp.MainActivity
import com.seatrend.lockerapp.R
import com.seatrend.lockerapp.base.BaseActivity
import com.seatrend.utilsdk.time.TimeCounter
import kotlinx.android.synthetic.main.activity_response.*


/**
 * Created by ly on 2020/8/12 11:05
 */
class ResponseActivity : BaseActivity(), TimeCounter.TimeCallBack {
    override fun initView() {

//        val str = getString(R.string.text_tip_2)  +"<font color='#0366d8'><bold>" +getString(R.string.text_tip_2_telephone)+ "</bold></font>"
//
//        tv_telephone.text = Html.fromHtml(str,0)
    }

    override fun bindEvent() {
        re_open.setOnClickListener {
            TimeCounter.getInstance().cancel()
            TimeCounter.getInstance().run(this)
        }
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
            timetv!!.text = getString(com.seatrend.lockerapp.R.string.time_limmit, thisTime / 1000)
        }
    }

    override fun TimeOutBack() {
        startActivityToTop(MainActivity::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.activity_response
    }
}