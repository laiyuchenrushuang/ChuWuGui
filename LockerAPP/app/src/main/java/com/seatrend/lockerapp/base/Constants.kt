package com.seatrend.lockerapp.base

/**
 * Created by ly on 2020/6/28 11:27
 */
public interface Constants {

    object APP {

        val VERSION = "版本号：1.0.0"
        val NETADDRESS = "ip_address"
        var TOKEN = "Authorization" //token
        val USER_NAME: String = "user_name"

    }

    companion object {

        val HPHM ="HPHM"
        val ONE_MIN: Long = 60*1000
        val FS = "FS"//储存方式
        val SAVE = "SAVE" //存
        val TAKE_OUT = "OUT"//取
    }

    object URL {


    }


}
