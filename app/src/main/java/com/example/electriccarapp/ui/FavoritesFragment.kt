package com.example.electriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.data.local.CarRepository
import com.example.electriccarapp.domain.Car
import com.example.electriccarapp.ui.adapter.CarAdapter

class FavoritesFragment : Fragment() {
    private lateinit var carsFavoritesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupList()
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun setupViews(view: View) {
        view.apply {
            carsFavoritesList = findViewById(R.id.rv_cars_favorite_list)
        }
    }

    private fun setupList() {
        refreshList()
    }

    private fun refreshList() {
        val carsArray = getCarsOnLocalDB()
        val carAdapter = CarAdapter(carsArray)
        carsFavoritesList.apply {
            visibility = View.VISIBLE
            adapter = carAdapter
        }
        carAdapter.carItemListing = { car ->
            val isDeleted = CarRepository(requireContext()).deleteIfExist(car)
            if (isDeleted) {
                refreshList()
            }
        }
    }

    private fun getCarsOnLocalDB(): List<Car> {
        val carRepository = CarRepository(requireContext())
        return carRepository.getAll()
    }
}