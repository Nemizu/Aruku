package com.example.aruku

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat

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
}