package com.example.tiptime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
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
//        binding.changeActivity.setOnClickListener() {
//            changeActivity()
//        }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    private fun changeActivity() {
        val intent = Intent(this, ChangeThemeActivity::class.java).apply {

        }
        startActivity(intent)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun calculateTip() {

        val serviceCost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        val selectedTip = binding.tipOptions.checkedRadioButtonId
        if (serviceCost == null) {
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

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}

