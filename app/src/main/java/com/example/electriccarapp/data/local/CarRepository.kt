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

    fun findCarById(id: Int) {
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
        val carItem = mutableListOf<Car>()
        with(cursor){
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                Log.d("ID -> ", itemId.toString())
            }
        }
        cursor.close()
    }
}