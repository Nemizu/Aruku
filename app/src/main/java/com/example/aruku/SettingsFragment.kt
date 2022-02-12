package com.example.aruku

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.format.Time
import android.text.method.DigitsKeyListener
import android.view.MenuItem
import android.widget.TimePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.*
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val mainactivity = this.activity as MainActivity
        setHasOptionsMenu(true)
        mainactivity.setupBack(true)
        val numberPreference1: EditTextPreference? = findPreference("100money")
        val numberPreference2: EditTextPreference? = findPreference("examount")
        //val timePreference: EditTextPreference? = findPreference("resettime")
        //val Dialog1 :DialogPreference? = findPreference("100money")
        numberPreference1?.setOnBindEditTextListener {
            it.keyListener = DigitsKeyListener.getInstance("01234456789")
            it.inputType = InputType.TYPE_CLASS_NUMBER
            it.filters = arrayOf(InputFilter.LengthFilter(4))
        }
        numberPreference2?.setOnBindEditTextListener {
            it.keyListener = DigitsKeyListener.getInstance("01234456789")
            it.inputType = InputType.TYPE_CLASS_NUMBER
            it.filters = arrayOf(InputFilter.LengthFilter(4))
        }
        /*
        timePreference?.setOnBindEditTextListener {
            it.inputType = InputType.TYPE_CLASS_DATETIME
        }*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           android.R.id.home ->{
               //findNavController().navigate(R.id.action_settingsFragment_to_statusDisplay)
               findNavController().popBackStack()
           }
            else ->{}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        val mainactivity = this.activity as MainActivity
        val shared = PreferenceManager.getDefaultSharedPreferences(mainactivity)
        when(preference.key){
            "reset"->{
                val examount = shared.getString("examount","100")?.toInt()
                if (examount != null) {
                    shared.edit().putInt("amount",examount).apply()
                    shared.edit().putInt("getmoney",0).apply()
                    shared.edit().putInt("steps",0).apply()
                }
                Toast.makeText(mainactivity,"リセットしました",Toast.LENGTH_SHORT).show()
            }
            /*
            "resettime"->{
                val timepicker = TimePickerDialog(mainactivity,TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                    fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        shared.edit().putString("resettime","${p1}:${p2}").apply()
                    }
                },1,1,false)
                timepicker.show()
            }*/
            else->{}
        }
        return super.onPreferenceTreeClick(preference)
    }
}