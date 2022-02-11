package com.example.aruku

import android.content.Context
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shared = PreferenceManager.getDefaultSharedPreferences(this)

        /*初回起動処理*/
        if(!shared.getBoolean("Launched",false)){
            shared.edit().putInt("amount",100).apply()
            shared.edit().putString("100money","10").apply()
            shared.edit().putString("examount","100").apply()
            shared.edit().putString("resettime","24:00").apply()
            shared.edit().putBoolean("Launched",true).apply()
            shared.edit().putInt("steps",0).apply()
            shared.edit().putInt("getmoney",0).apply()
        }
    }

    fun setupBack(enable: Boolean){
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(enable)
        actionbar?.setHomeButtonEnabled(enable)

    }
}
