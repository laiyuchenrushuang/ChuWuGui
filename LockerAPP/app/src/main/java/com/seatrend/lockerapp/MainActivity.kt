package com.seatrend.lockerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.seatrend.lockerapp.base.Constants
import com.seatrend.lockerapp.ui.IdentityActivity
import com.seatrend.utilsdk.time.TimeCounter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        bindEvent()
        TimeCounter.getInstance().cancel()
    }

    private fun bindEvent() {
        save.setOnClickListener {
            val intent = Intent()
            intent.putExtra(Constants.FS, Constants.SAVE)
            intent.setClass(this, IdentityActivity::class.java)
            startActivity(intent)

        }

        take_out.setOnClickListener {
            val intent = Intent()
            intent.putExtra(Constants.FS, Constants.TAKE_OUT)
            intent.setClass(this, IdentityActivity::class.java)
            startActivity(intent)
        }
    }
}
