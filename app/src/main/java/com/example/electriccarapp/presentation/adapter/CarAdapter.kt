package com.example.electriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electriccarapp.R

class CarAdapter(private val cars: Array<String>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    // Create a new view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    // Get the content of the view and trade for actual item values
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = cars[position]
    }

    // Returns the total cars list size
    override fun getItemCount(): Int {
        return cars.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.tv_car_price_value)
        }
    }
}

