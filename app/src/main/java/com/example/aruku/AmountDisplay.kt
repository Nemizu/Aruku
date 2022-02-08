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

class AmountDisplay : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_amount_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainactivity = this.activity as MainActivity
        val shared = PreferenceManager.getDefaultSharedPreferences(mainactivity)

        view.findViewById<TextView>(R.id.amount).text = shared.getInt("amount",100).toString() + "円"


        view.findViewById<Button>(R.id.use).setOnClickListener {
            findNavController().navigate(R.id.action_amountDisplay_to_paymentDisplay)
        }
    }

}