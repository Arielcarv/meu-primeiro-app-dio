package com.example.electriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.data.CarFactory
import com.example.electriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var btnRedirect: Button
    private lateinit var carsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
        setupList()
    }

    private fun setupViews() {
        btnRedirect = findViewById(R.id.btn_redirect)
        carsList = findViewById(R.id.rv_cars_list)
    }

    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        carsList.adapter = adapter
    }


    private fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }
}
