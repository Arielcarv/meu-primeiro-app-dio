package com.example.electriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.example.electriccarapp.domain.Car

class CarRepository(private val context: Context) {

    fun save(car: Car): Long? {
        try {
            val dbHelper = CarsDBHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CarsContract.CarEntry.COLUMN_CAR_ID, car.id)
                put(CarsContract.CarEntry.COLUMN_NAME_PRICE, car.price)
                put(CarsContract.CarEntry.COLUMN_NAME_PRICE, car.price)
                put(CarsContract.CarEntry.COLUMN_NAME_BATTERY, car.battery)
                put(CarsContract.CarEntry.COLUMN_NAME_POWER, car.power)
                put(CarsContract.CarEntry.COLUMN_NAME_CHARGE, car.charge)
                put(CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL, car.photoUrl)
            }
            return db?.insert(CarsContract.CarEntry.TABLE_NAME, null, values)
        } catch (ex: Exception) {
            ex.message?.let { Log.e("Error on data insertion", it) }
        }
        return (0L)
    }

    fun findCarById(id: Int): Car {
        val dbHelper = CarsDBHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            CarsContract.CarEntry.COLUMN_CAR_ID,
            CarsContract.CarEntry.COLUMN_NAME_PRICE,
            CarsContract.CarEntry.COLUMN_NAME_BATTERY,
            CarsContract.CarEntry.COLUMN_NAME_POWER,
            CarsContract.CarEntry.COLUMN_NAME_CHARGE,
            CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL,
        )
        val filter = "${CarsContract.CarEntry.COLUMN_CAR_ID} = ?"
        val filterValues = arrayOf(id.toString())
        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            columns,
            filter,
            filterValues,
            null,
            null,
            null
        )

        var carItem = Car(
            id = 0,
            price = "",
            battery = "",
            power = "",
            charge = "",
            photoUrl = "",
            isFavorite = false
        )
        with(cursor) {
            while (moveToNext()) {
                val itemId = getInt(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_CAR_ID))
                val price =
                    getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PRICE))
                val battery =
                    getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_BATTERY))
                val power =
                    getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_POWER))
                val charge =
                    getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_CHARGE))
                val photoUrl =
                    getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL))

                carItem = Car(
                    id = itemId,
                    price = price,
                    battery = battery,
                    power = power,
                    charge = charge,
                    photoUrl = photoUrl,
                    isFavorite = false
                )
                Log.d("DEBUG CURSOR", carItem.toString())
            }
        }
        return carItem
    }

    fun saveIfNotExist(car: Car) {
        val carObject: Car = findCarById(car.id)
        if (carObject.id == ID_WHEN_NO_CAR) {
            save(car)
        }
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}