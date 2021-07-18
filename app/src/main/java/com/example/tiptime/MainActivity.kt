package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {

        val serviceCost = binding.costOfService.text.toString().toDouble()
        val selectedTip = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when (selectedTip) {
            R.id.option_amazing -> 0.20
            R.id.option_good -> 0.15
            else -> 0.10
        }

        var tip = serviceCost * tipPercentage
        val roundUp = binding.roundedTipSwitch.isChecked
        if (roundUp) {
            tip = ceil(serviceCost * tipPercentage)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}