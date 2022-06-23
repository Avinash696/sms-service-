package com.example.smsservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SmsBroadCast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        val bundle = intent!!.extras

        val smsObj = bundle?.get("pdus")
            Log.d("rawat", "onReceive: $smsObj")

//        for ( obj in  smsObj  ){
//            f
//        }
    }
}