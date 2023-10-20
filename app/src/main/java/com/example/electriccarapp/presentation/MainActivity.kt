package com.example.electriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.electriccarapp.R

class MainActivity : AppCompatActivity() {
    lateinit var btnRedirect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
    }

    fun setupViews() {
        btnRedirect = findViewById<Button>(R.id.btn_redirect)
    }

    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }
}
