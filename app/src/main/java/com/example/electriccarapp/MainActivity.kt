package com.example.electriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var chargePrice: EditText
    lateinit var btnCalculate: Button
    lateinit var kmsDriven: EditText
    lateinit var automonyResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
    }

    fun setupViews() {
        chargePrice = findViewById<EditText>(R.id.et_charge_price)
        btnCalculate = findViewById<Button>(R.id.btn_calculate)
        kmsDriven = findViewById<EditText>(R.id.et_kms_driven)
        automonyResult = findViewById<TextView>(R.id.tv_autonomy_result)
    }

    fun setupListeners() {
        btnCalculate.setOnClickListener {
            calculateCarAutonomy()
        }
    }

    fun calculateCarAutonomy() {
        val chargePrice = chargePrice.text.toString().toFloat()
        val kmsDriven = kmsDriven.text.toString().toFloat()
        val autonomy = chargePrice / kmsDriven

        automonyResult.text = autonomy.toString()
    }
}