package com.example.aruku

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val mainactivity = this.activity as MainActivity
        setHasOptionsMenu(true)
        mainactivity.setupBack(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           android.R.id.home ->{
               findNavController().navigate(R.id.action_settingsFragment_to_statusDisplay)
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
                }
                Toast.makeText(mainactivity,"リセットしました",Toast.LENGTH_SHORT).show()
            }
            else->{}
        }
        return super.onPreferenceTreeClick(preference)
    }
}