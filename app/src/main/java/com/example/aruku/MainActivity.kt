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
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calender = Calendar.getInstance()
        val myformat = SimpleDateFormat("yyyy-MM-dd")
        val date = myformat.format(calender.time)

        /*権限の確認（動作未確認）*/
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
        /*日にちをまたいだ時*/
        if(shared.getString("lastdate","") != date){
            val examount = shared.getString("examount","100")?.toInt()
            if (examount != null) {
                shared.edit().putInt("amount",examount).apply()
                shared.edit().putInt("getmoney",0).apply()
                shared.edit().putInt("steps",0).apply()
            }
            shared.edit().putString("lastdate",date).apply()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /*アクションバーのボタンの有効化*/
    fun setupBack(enable: Boolean){
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(enable)
        actionbar?.setHomeButtonEnabled(enable)

    }
}
