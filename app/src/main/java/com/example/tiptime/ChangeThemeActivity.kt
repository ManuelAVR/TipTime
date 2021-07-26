package com.example.tiptime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityChangeThemeBinding
import com.example.tiptime.databinding.ActivityMainBinding

class ChangeThemeActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }



}