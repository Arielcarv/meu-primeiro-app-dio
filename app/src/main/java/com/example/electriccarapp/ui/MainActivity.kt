package com.example.electriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.electriccarapp.R
import com.example.electriccarapp.data.CarFactory
import com.example.electriccarapp.ui.adapter.CarAdapter
import com.example.electriccarapp.ui.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var btnCalculator: Button
    private lateinit var carsList: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Android LifeCycle", "CREATE")

        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
        setupList()
        setupTabs()
    }

    private fun setupViews() {
        tabLayout = findViewById(R.id.tab_layout)
        btnCalculator = findViewById(R.id.btn_calculator)
        carsList = findViewById(R.id.rv_cars_list)
        viewPager2 = findViewById(R.id.vp_view_pager)
    }

    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        carsList.adapter = adapter
    }

    private fun setupTabs() {
        val tabsAdapter = TabAdapter(this)
        viewPager2.adapter = tabsAdapter
    }

    private fun setupListeners() {
        btnCalculator.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager2.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })

    }
}
