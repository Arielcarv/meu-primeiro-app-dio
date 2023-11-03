package com.example.electriccarapp.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.data.CarFactory
import com.example.electriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {
    private lateinit var fabCalculator: FloatingActionButton
    private lateinit var carsList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        val adapter = CarAdapter(CarFactory.list)
        carsList.adapter = adapter
    }

    private fun setupListeners() {
        fabCalculator.setOnClickListener {
            GetCarInformations().execute("https://igorbag.github.io/cars-api/cars.json")
//            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }

    inner class GetCarInformations : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "Iniciando...")
        }

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])
                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 6000
                urlConnection.readTimeout = 6000
                val inString = urlConnection.inputStream.bufferedReader().use {
                    it.readText()
                }
                publishProgress(inString)
            } catch (ex: Exception) {
                Log.e("Error", "InputStream processing error.")
            } finally {
                if (urlConnection != null) {
                    urlConnection?.disconnect()
                }
            }
            return ""
        }

        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val price = jsonArray.getJSONObject(i).getString("preco")
                    val battery = jsonArray.getJSONObject(i).getString("bateria")
                    val power = jsonArray.getJSONObject(i).getString("potencia")
                    val charge = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("ID -> ", id)
                    Log.d("PRICE -> ", price)
                    Log.d("battery -> ", battery)
                    Log.d("power -> ", power)
                    Log.d("CHARGE -> ", charge)
                }
            } catch (ex: Exception) {
                Log.e("Error", "Update progress error.")
            }
        }
    }
}