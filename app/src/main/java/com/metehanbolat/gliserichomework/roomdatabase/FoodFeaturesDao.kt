package com.metehanbolat.gliserichomework.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.metehanbolat.gliserichomework.model.FoodFeaturesModel
import com.metehanbolat.gliserichomework.model.CategoryModel
import com.metehanbolat.gliserichomework.model.CategoryWithFoodFeatures

@Dao
interface FoodFeaturesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFoodFeatures(foodFeaturesModel: FoodFeaturesModel)

    @Update
    suspend fun updateFoodFeatures(foodFeaturesModel: FoodFeaturesModel)

    @Delete
    suspend fun deleteFoodFeatures(foodFeaturesModel: FoodFeaturesModel)

    @Query("SELECT * FROM food_features WHERE foodName LIKE :searchQuery OR glycemicIndex LIKE :searchQuery OR carbohydrates LIKE :searchQuery OR calories LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : LiveData<List<FoodFeaturesModel>>

    @Query("SELECT * FROM food_features ORDER BY id ASC")
    fun readAllData(): LiveData<List<FoodFeaturesModel>>

    @Transaction
    @Query("SELECT * FROM food_features_category WHERE category = :category")
    suspend fun getCategoryWithFoodFeatures(category: String): List<CategoryWithFoodFeatures>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(categoryModel: CategoryModel)

    @Delete
    suspend fun deleteCategory(categoryModel: CategoryModel)

    @Query("DELETE FROM food_features WHERE category = :category")
    suspend fun deleteFoodFeaturesWithCategory(category: String)

    @Query("SELECT * FROM food_features_category ORDER BY category ASC")
    fun readAllCategories(): LiveData<List<CategoryModel>>

    @Query("SELECT * FROM food_features WHERE favourite = 1")
    suspend fun getFavouriteFoodFeatures(): List<FoodFeaturesModel>

}