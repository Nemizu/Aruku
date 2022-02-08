package com.example.aruku

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager

class StatusDisplay : Fragment() {

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
        val mainactivity = this.activity as MainActivity
        val shared = PreferenceManager.getDefaultSharedPreferences(mainactivity)
        view.findViewById<TextView>(R.id.value3).text = shared.getString("1000money","0") + "円"
        view.findViewById<TextView>(R.id.value4).text = shared.getString("resettime","0:00")
        view.findViewById<TextView>(R.id.value5).text = shared.getString("examount","1000") + "円"
        mainactivity.setupBack(false)

        view.findViewById<Button>(R.id.setting).setOnClickListener {
            findNavController().navigate(R.id.action_statusDisplay_to_settingsFragment)
        }
    }
}