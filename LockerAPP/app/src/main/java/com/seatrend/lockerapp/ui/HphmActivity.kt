package com.seatrend.lockerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import com.seatrend.lockerapp.MainActivity
import com.seatrend.lockerapp.R
import com.seatrend.lockerapp.base.Constants
import com.seatrend.lockerapp.base.BaseActivity
import com.seatrend.utilsdk.time.TimeCounter
import com.seatrend.utilsdk.utils.CarHphmUtils
import com.seatrend.utilsdk.utils.cache.ACache
import kotlinx.android.synthetic.main.hphm_activity.*

/**
 * Created by ly on 2020/8/11 16:59
 */
class HphmActivity : BaseActivity(), TimeCounter.TimeCallBack {
    override fun initView() {
        hphm_edit.transformationMethod = CarHphmUtils.TransInformation()
        getCache()
    }

    private fun getCache() {

        if (!TextUtils.isEmpty(ACache.get(this).getAsString(Constants.HPHM))) {
            val count = sheng.adapter.count
            val selectCache = ACache.get(this).getAsString(Constants.HPHM)
            for (i in 0 until count) {

                if (selectCache == sheng.adapter.getItem(i)) {
                    sheng.setSelection(i)
                    break
                }
            }
        }
    }


    override fun bindEvent() {
        btn_next.setOnClickListener {
//            if (TextUtils.isEmpty(hphm_edit.text.toString()) || !CarHphmUtils.macherString(sheng.selectedItem.toString() + hphm_edit.text.toString().toUpperCase())) {
//                showErrorDialog(resources.getString(R.string.hphm_tip_error_1))
//                return@setOnClickListener
//            }

            saveCache()
            startActivity(Intent(this, ResponseActivity::class.java))
        }
    }

    private fun saveCache() {
        ACache.get(this).put(Constants.HPHM, sheng.selectedItem.toString())
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
        return R.layout.hphm_activity
    }
}