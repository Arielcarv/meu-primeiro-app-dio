package com.example.electriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.data.CarsAPI
import com.example.electriccarapp.data.local.CarRepository
import com.example.electriccarapp.domain.Car
import com.example.electriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {
    private lateinit var fabCalculator: FloatingActionButton
    private lateinit var carsList: RecyclerView
    private lateinit var progressWidget: ProgressBar
    private lateinit var noInternetImage: ImageView
    private lateinit var noInternetText: TextView
    private lateinit var carsApi: CarsAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupViews(view)
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        if (checkInternetConnectivity(requireContext())) {
            getAllCars()
        } else {
            emptyState()
        }
    }

    private fun setupRetrofit() {
        val builder = Retrofit.Builder().baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        carsApi = builder.create(CarsAPI::class.java)
    }

    private fun getAllCars() {
        carsApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    progressWidget.visibility = View.GONE
                    noInternetImage.visibility = View.GONE
                    noInternetText.visibility = View.GONE
                    response.body()?.let { setupList(it) }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun emptyState() {
        progressWidget.isVisible = false
        carsList.isVisible = false
        noInternetImage.isVisible = true
        noInternetText.isVisible = true
    }

    private fun setupViews(view: View) {
        view.apply {
            fabCalculator = findViewById(R.id.fab_calculator)
            carsList = findViewById(R.id.rv_cars_list)
            progressWidget = findViewById(R.id.pb_loader)
            noInternetImage = findViewById(R.id.iv_no_wifi)
            noInternetText = findViewById(R.id.tv_no_wifi)
        }
    }

    private fun setupList(carsArray: List<Car>) {
        val carAdapter = CarAdapter(carsArray)
//        val carsArrayFromLocalDatabase = getCarsOnLocalDB()
        carsList.apply {
            visibility = View.VISIBLE
            adapter = carAdapter
        }
        carAdapter.carItemListing = { car ->
            val isSaved = CarRepository(requireContext()).saveIfNotExist(car)
        }
    }

    private fun setupListeners() {
        fabCalculator.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }

    private fun checkInternetConnectivity(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    private fun getCarsOnLocalDB(): List<Car> {
        val carRepository = CarRepository(requireContext())
        return carRepository.getAll()
    }
}