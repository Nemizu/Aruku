package com.example.aruku

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager

class PaymentDisplay : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val display = view.findViewById<EditText>(R.id.paynum)
        val mainactivity = this.activity as MainActivity
        val shared = PreferenceManager.getDefaultSharedPreferences(mainactivity)

        view.findViewById<Button>(R.id.pay10).setOnClickListener {
            display.setText(editablePlusInt(display.text,10,shared.getInt("amount",100)))
        }
        view.findViewById<Button>(R.id.pay100).setOnClickListener {
            display.setText(editablePlusInt(display.text,100,shared.getInt("amount",100)))
        }
        view.findViewById<Button>(R.id.mpay10).setOnClickListener {
            display.setText(editablePlusInt(display.text,-10,shared.getInt("amount",100)))
        }
        view.findViewById<Button>(R.id.mpay100).setOnClickListener {
            display.setText(editablePlusInt(display.text,-100,shared.getInt("amount",100)))
        }
        view.findViewById<Button>(R.id.back).setOnClickListener {
            findNavController().navigate(R.id.action_paymentDisplay_to_amountDisplay)
        }
        view.findViewById<Button>(R.id.assept).setOnClickListener {
            if(display.text.toString() != ""){//なぜか&&を使用すると落ちるので
                if(shared.getInt("amount",100) >= display.text.toString().toInt()){
                    shared.edit().putInt("amount",shared.getInt("amount",100) - display.text.toString().toInt()).apply()
                    findNavController().navigate(R.id.action_paymentDisplay_to_amountDisplay)
                }
            }
        }
    }

    private fun editablePlusInt(editable: Editable, plus: Int, limit: Int):String{
        if (limit - editable.toString().toInt() < plus && plus > 0){
            return limit.toString()
        }else if(editable.toString().toInt() + plus < 0 && plus < 0){
            return "0"
        }
        return editable.toString().toIntOrNull()?.plus(plus).toString()
    }
}