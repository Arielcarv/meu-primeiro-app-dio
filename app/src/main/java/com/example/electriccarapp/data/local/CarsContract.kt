package com.example.electriccarapp.data.local

import android.provider.BaseColumns

object CarsContract {
    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_BATTERY = "battery"
        const val COLUMN_NAME_POWER = "power"
        const val COLUMN_NAME_CHARGE = "charge"
        const val COLUMN_NAME_PHOTO_URL = "photoUrl"
    }

    const val CAR_TABLE = "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${CarEntry.COLUMN_NAME_PRICE} TEXT," +
            "${CarEntry.COLUMN_NAME_POWER} TEXT," +
            "${CarEntry.COLUMN_NAME_BATTERY} TEXT," +
            "${CarEntry.COLUMN_NAME_CHARGE} TEXT," +
            "${CarEntry.COLUMN_NAME_PHOTO_URL} TEXT" +
            ")"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"
}