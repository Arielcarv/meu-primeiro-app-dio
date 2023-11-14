package com.example.electriccarapp.data.local

import android.provider.BaseColumns

object CarsContract {
    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_BATTERY = "battery"
        const val COLUMN_NAME_POWER = "power"
        const val COLUMN_NAME_CHARGE = "charge"
        const val COLUMN_NAME_PHOTO_URL = "photoUrl"
    }
}