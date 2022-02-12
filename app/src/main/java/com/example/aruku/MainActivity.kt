package com.example.aruku

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.PackageManagerCompat
import androidx.preference.PreferenceManager
import java.security.Permission
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(android.Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED){

            }else{
                requestPermissions(arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),1)
            }
        }

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

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun setupBack(enable: Boolean){
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(enable)
        actionbar?.setHomeButtonEnabled(enable)

    }
}
