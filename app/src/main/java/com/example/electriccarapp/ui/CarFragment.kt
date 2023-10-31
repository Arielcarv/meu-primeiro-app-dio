package com.example.electriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.data.CarFactory
import com.example.electriccarapp.ui.adapter.CarAdapter

class CarFragment : Fragment() {
    private lateinit var btnCalculator: Button
    private lateinit var carsList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
        setupList()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews(view: View) {
        view.apply {
            btnCalculator = findViewById(R.id.btn_calculator)
            carsList = findViewById(R.id.rv_cars_list)
        }
    }
    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        carsList.adapter = adapter
    }

    private fun setupListeners() {
        btnCalculator.setOnClickListener {
//            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }
}