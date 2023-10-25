package com.example.electriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R

class MainActivity : AppCompatActivity() {
    lateinit var btnRedirect: Button
    lateinit var cars_list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
        setupList()
    }

    fun setupViews() {
        btnRedirect = findViewById<Button>(R.id.btn_redirect)
        cars_list = findViewById<RecyclerView>(R.id.rv_cars_list)
    }

    fun setupList() {
        var data = arrayOf(
            "Cupcake",
            "Donut",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean"
        )
//        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
//        cars_list.adapter = adapter
    }


    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }
}
