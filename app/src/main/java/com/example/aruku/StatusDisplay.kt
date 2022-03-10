package com.example.aruku

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_amount_display.view.*

class StatusDisplay : Fragment() ,SensorEventListener{
    private lateinit var mManager: SensorManager
    private lateinit var mSensor: Sensor
    private lateinit var shared: SharedPreferences
    private lateinit var mainactivity: MainActivity
    private lateinit var value1: TextView
    private lateinit var value2: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_status_display, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainactivity = this.activity as MainActivity
        mainactivity.stopService(Intent(mainactivity,CountService::class.java))
        mManager = mainactivity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        /*TYPE_STEP_COUNTERを使用する場合*/
        //countSensor = mManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        //mManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI)

        shared = PreferenceManager.getDefaultSharedPreferences(mainactivity)
        mainactivity.setupBack(false)
        value1 = view.findViewById(R.id.value1)
        value2 = view.findViewById(R.id.value2)

        if(shared.getString("100money","100") == ""){
            shared.edit().putString("100money","10").apply()
        }
        if(shared.getString("resettime","100") == ""){
            shared.edit().putString("resettime","24:00").apply()
        }
        if(shared.getString("examount","100") == ""){
            shared.edit().putString("examount","100").apply()
        }
        view.findViewById<TextView>(R.id.value3).text = shared.getString("100money","10") + "円"
        view.findViewById<TextView>(R.id.value4).text = shared.getString("resettime","24:00")
        view.findViewById<TextView>(R.id.value5).text = shared.getString("examount","100") + "円"
        //設定画面への変遷
        view.findViewById<Button>(R.id.setting).setOnClickListener {
            findNavController().navigate(R.id.action_statusDisplay_to_settingsFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        mManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_UI)
        value1.text = shared.getInt("steps",0).toString() + "歩"
        value2.text = shared.getInt("getmoney",0).toString() + "円"
    }

    override fun onStop() {
        super.onStop()
        mManager.unregisterListener(this)
        mainactivity.startService(Intent(mainactivity,CountService::class.java))
    }


    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(p0: SensorEvent?) {
        shared.edit().putInt("steps",shared.getInt("steps",0)+1).apply()
        value1.text = shared.getInt("steps",0).toString() + "歩"
        if(shared.getInt("steps",0) % 100 == 0){
            shared.edit().putInt("amount",shared.getInt("amount",100) + shared.getString("100money","100")!!.toInt()).apply()
            shared.edit().putInt("getmoney",shared.getInt("getmoney",0) + shared.getString("100money","100")!!.toInt()).apply()
            value2.text = shared.getInt("getmoney",0).toString() + "円"

        }
        /*TYPE_STEP_COUNTERが使えた場合*/
        /*if(p0!=null){
            if(p0.sensor.type == Sensor.TYPE_STEP_DETECTOR){

            }*/
            /*if(p0.sensor.type == Sensor.TYPE_STEP_COUNTER){
                shared.edit().putInt("steps",shared.getInt("steps",0)+1).apply()
                value1.text = shared.getInt("steps",0).toString()
            }*/
        }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}