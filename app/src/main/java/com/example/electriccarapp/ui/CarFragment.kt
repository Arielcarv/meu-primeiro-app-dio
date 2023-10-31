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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment : Fragment() {
    private lateinit var fabCalculator: FloatingActionButton
    private lateinit var carsList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupList()
        setupListeners()
    }

    private fun setupViews(view: View) {
        view.apply {
            fabCalculator = findViewById(R.id.fab_calculator)
            carsList = findViewById(R.id.rv_cars_list)
        }
    }

    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        carsList.adapter = adapter
    }

    private fun setupListeners() {
        fabCalculator.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }
}