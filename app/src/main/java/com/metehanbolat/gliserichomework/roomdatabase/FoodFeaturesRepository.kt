package com.metehanbolat.gliserichomework.roomdatabase

import androidx.lifecycle.LiveData

class FoodFeaturesRepository(private val foodFeaturesDao: FoodFeaturesDao) {

    val readAllData: LiveData<List<FoodFeaturesModel>> = foodFeaturesDao.readAllData()

    suspend fun addFoodFeatures(foodFeaturesModel: FoodFeaturesModel) {
        foodFeaturesDao.addFoodFeatures(foodFeaturesModel)
    }
}