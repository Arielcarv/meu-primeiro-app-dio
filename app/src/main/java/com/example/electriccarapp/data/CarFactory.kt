package com.example.electriccarapp.data

import com.example.electriccarapp.domain.Car

object CarFactory {
    val list = listOf<Car>(
        Car(
            id = 1,
            price = "R$100.000",
            battery = "100 kWh",
            power = "100 hp",
            charge = "10 min",
            photoUrl = "www.google.com",
        ),
        Car(
            id = 2,
            price = "R$200.000",
            battery = "200 kWh",
            power = "200 hp",
            charge = "20 min",
            photoUrl = "www.google.com",
        )
    )
}