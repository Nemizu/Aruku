package com.example.aruku

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.PreferenceChangeListener

class AmountDisplay : Fragment() ,SharedPreferences.OnSharedPreferenceChangeListener{

    private lateinit var amount:TextView
    private lateinit var shared:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_amount_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainactivity = this.activity as MainActivity
        shared = PreferenceManager.getDefaultSharedPreferences(mainactivity)
        amount = view.findViewById<TextView>(R.id.amount)
        amount.text = shared.getInt("amount",100).toString() + "円"
        shared.registerOnSharedPreferenceChangeListener(this)


        view.findViewById<Button>(R.id.use).setOnClickListener {
            findNavController().navigate(R.id.action_amountDisplay_to_paymentDisplay)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        shared.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if(p1 == "amount"){
            amount.text = shared.getInt("amount",100).toString() + "円"
        }
    }
}