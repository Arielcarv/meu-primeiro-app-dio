package com.example.electriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        Log.d("Android LifeCycle", "CREATE")

        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
        setupList()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Android LifeCycle", "RESUME")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Android LifeCycle", "START")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Android LifeCycle", "PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Android LifeCycle", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Android LifeCycle", "DESTROY")
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
