package com.example.electriccarapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.electriccarapp.R

class CalculateAutonomyActivity : AppCompatActivity() {
    lateinit var chargePrice: EditText
    lateinit var btnCalculate: Button
    lateinit var kmsDriven: EditText
    lateinit var autonomyResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_autonomy)

        setupViews()
        setupListeners()
    }

    fun setupViews() {
        chargePrice = findViewById<EditText>(R.id.et_charge_price)
        btnCalculate = findViewById<Button>(R.id.btn_calculate)
        kmsDriven = findViewById<EditText>(R.id.et_kms_driven)
        autonomyResult = findViewById<TextView>(R.id.tv_autonomy_result)
    }

    fun setupListeners() {
        btnCalculate.setOnClickListener {
            calculateCarAutonomy()
        }
    }

    fun calculateCarAutonomy() {
        try {
            val chargePrice = chargePrice.text.toString().toFloat()
            val kmsDriven = kmsDriven.text.toString().toFloat()
            val autonomy = chargePrice / kmsDriven
            autonomyResult.text = autonomy.toString()
        } catch (e: NumberFormatException) {
            autonomyResult.text = "Invalid input"
        }
    }
}
