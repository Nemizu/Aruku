package com.example.aruku

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import androidx.preference.PreferenceManager
/*裏で動かすため*/
class CountService : Service(), SensorEventListener{

    private lateinit var mManager: SensorManager
    private lateinit var mSensor: Sensor
    private lateinit var shared: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        shared = PreferenceManager.getDefaultSharedPreferences(this)
        mManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        mManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_UI)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        mManager.unregisterListener(this)
        super.onDestroy()
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        shared.edit().putInt("steps",shared.getInt("steps",0)+1).apply()
        if(shared.getInt("steps",0) % 100 == 0){
            shared.edit().putInt("amount",shared.getInt("amount",100) + shared.getString("100money","100")!!.toInt()).apply()
            shared.edit().putInt("getmoney",shared.getInt("getmoney",0) + shared.getString("100money","100")!!.toInt()).apply()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}