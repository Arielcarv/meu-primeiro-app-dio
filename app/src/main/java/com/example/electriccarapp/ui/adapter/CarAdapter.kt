package com.example.electriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R
import com.example.electriccarapp.domain.Car

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    // Create a new view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    // Get the content of the view and trade for actual item values
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.text = cars[position].price
        holder.battery.text = cars[position].battery
        holder.power.text = cars[position].power
        holder.charge.text = cars[position].charge
    }

    // Returns the total cars list size
    override fun getItemCount(): Int {
        return cars.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val battery: TextView
        val power: TextView
        val charge: TextView

        init {
            view.apply {
                price = findViewById(R.id.tv_car_price_value)
                battery = findViewById(R.id.tv_car_battery_value)
                power = findViewById(R.id.tv_car_power_value)
                charge = findViewById(R.id.tv_car_charge_value)
            }
        }
    }
}

