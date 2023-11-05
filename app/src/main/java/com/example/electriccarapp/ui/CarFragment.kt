package com.example.electriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.domain.Car
import com.example.electriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {
    private lateinit var fabCalculator: FloatingActionButton
    private lateinit var carsList: RecyclerView
    private var carsArray: ArrayList<Car> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callService()
        setupViews(view)
        setupListeners()
    }

    private fun setupViews(view: View) {
        view.apply {
            fabCalculator = findViewById(R.id.fab_calculator)
            carsList = findViewById(R.id.rv_cars_list)
        }
    }

    private fun setupList() {
        val adapter = CarAdapter(carsArray)
        carsList.adapter = adapter
    }

    private fun setupListeners() {
        fabCalculator.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun callService() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val json = getUrlData("https://igorbag.github.io/cars-api/cars.json")
                processJsonData(json)
            } catch (ex: Exception) {
                Log.e("Error", "Error fetching data: ${ex.message}")
            }
        }
    }

    private fun getUrlData(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.readTimeout = 6000
        connection.connectTimeout = 6000
        connection.setRequestProperty("Accept", "application/json")
        return try {
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                response.toString()
            } else {
                throw IOException("HTTP Error: ${connection.responseCode}")
            }
        } finally {
            connection.disconnect()
        }
    }

    private suspend fun processJsonData(jsonData: String) {
        withContext(Dispatchers.Main) {
            try {
                val jsonArray = JSONTokener(jsonData).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val price = jsonArray.getJSONObject(i).getString("preco")
                    val battery = jsonArray.getJSONObject(i).getString("bateria")
                    val power = jsonArray.getJSONObject(i).getString("potencia")
                    val charge = jsonArray.getJSONObject(i).getString("recarga")
                    val photoUrl = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("ID -> ", id)
                    Log.d("PRICE -> ", price)
                    Log.d("battery -> ", battery)
                    Log.d("power -> ", power)
                    Log.d("CHARGE -> ", charge)
                    val carsModel = Car(
                        id = id.toInt(),
                        price = price,
                        battery = battery,
                        power = power,
                        charge = charge,
                        photoUrl = photoUrl
                    )
                    carsArray.add(carsModel)
                    Log.d("Model -> ", carsModel.toString())
                }
                setupList()
            } catch (ex: Exception) {
                Log.e("Error", "Error processing JSON data: ${ex.message}")
            }
        }
    }
}