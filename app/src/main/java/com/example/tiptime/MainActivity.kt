package com.example.tiptime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
        binding.changeActivity.setOnClickListener(){
            changeActivity()
        }
    }

    private fun changeActivity() {
        val intent = Intent(this, ChangeThemeActivity::class.java).apply {

        }
        startActivity(intent)
    }


    private fun calculateTip() {

        val serviceCost = binding.serviceQuestion.text.toString().toDoubleOrNull()
        val selectedTip = binding.tipOptions.checkedRadioButtonId
        if(serviceCost == null){
            return
        }
        val tipPercentage = when (selectedTip) {
            R.id.option_amazing -> 0.20
            R.id.option_good -> 0.15
            else -> 0.10
        }

        var tip = serviceCost * tipPercentage

        if (binding.roundedTipSwitch.isChecked) {
            tip = ceil(serviceCost * tipPercentage)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}

