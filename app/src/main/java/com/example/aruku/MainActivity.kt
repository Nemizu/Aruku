package com.example.aruku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shared = PreferenceManager.getDefaultSharedPreferences(this)
        if(!shared.getBoolean("Launched",false)){
            shared.edit().putInt("amount",100).apply()
            shared.edit().putBoolean("Launched",true).apply()
        }
    }

    fun setupBack(enable: Boolean){
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(enable)
        actionbar?.setHomeButtonEnabled(enable)

    }
}
