package com.metehanbolat.gliserichomework.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.metehanbolat.gliserichomework.roomdatabase.FoodFeaturesDatabase
import com.metehanbolat.gliserichomework.roomdatabase.FoodFeaturesModel
import com.metehanbolat.gliserichomework.roomdatabase.FoodFeaturesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Document

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val firstDataList = MutableLiveData<ArrayList<String>>()
    private val readAllData: LiveData<List<FoodFeaturesModel>>
    private val repository: FoodFeaturesRepository

    suspend fun getData(document: Document) {
        withContext(Dispatchers.Main) {
            firstDataList.value = arrayListOf()
            val element = document.select("tbody")
            for (tr in element.select("tr")){
                for (td in tr.select("td")){
                    firstDataList.value?.add(td.text())
                    firstDataList.notifyObserver()
                }
            }
        }
    }

    init {
        val foodFeaturesDao = FoodFeaturesDatabase.getDatabase(application).foodFeaturesDao()
        repository = FoodFeaturesRepository(foodFeaturesDao)
        readAllData = repository.readAllData
    }

    fun addFoodFeatures(foodFeaturesModel: FoodFeaturesModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFoodFeatures(foodFeaturesModel)
        }
    }

}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.postValue(this.value)
}