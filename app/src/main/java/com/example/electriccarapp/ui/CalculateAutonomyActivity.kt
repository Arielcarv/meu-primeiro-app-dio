package com.example.electriccarapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.electriccarapp.R

class CalculateAutonomyActivity : AppCompatActivity() {
    lateinit var chargePrice: EditText
    lateinit var btnCalculate: Button
    lateinit var kmsDriven: EditText
    lateinit var autonomyResult: TextView
    lateinit var btnClose: ImageView

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
        btnClose = findViewById<ImageView>(R.id.iv_close)
    }

    fun setupListeners() {
        btnCalculate.setOnClickListener {
            calculateCarAutonomy()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }

    fun calculateCarAutonomy() {
        try {
            val chargePrice = chargePrice.text.toString().toFloat()
            val kmsDriven = kmsDriven.text.toString().toFloat()
            val autonomy = chargePrice / kmsDriven
            autonomyResult.text = autonomy.toString()
            saveSharedPreference(autonomy)
        } catch (e: NumberFormatException) {
            autonomyResult.text = "Invalid input"
        }
    }

    fun saveSharedPreference(result: Float) {
        val sharedPreference = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPreference.edit()) {
            putFloat(getString(R.string.saved_calculus), result)
            apply()
        }
    }
}
