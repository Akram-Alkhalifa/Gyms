package com.example.test

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GymsViewModel(
    private var stateHandle : SavedStateHandle
):ViewModel(){
    var state by mutableStateOf(emptyList<Gym>())
    private var apiService : GymsApiService


    init {
        val retrofit:Retrofit=Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            ).baseUrl("https://ak-gyms-default-rtdb.firebaseio.com/")
            .build()
        apiService=retrofit.create(GymsApiService::class.java)

        getGyms()
    }

 private fun getGyms(){
    val gyms=apiService.getGyms()

  }

    override fun onCleared() {
        super.onCleared()
    }
    fun toggleFavouriteState(gymId: Int){
        val gyms= state.toMutableList()
        val itemIndex=gyms.indexOfFirst { it.id==gymId }
        gyms[itemIndex]=gyms[itemIndex].copy(isFavourite = !gyms[itemIndex].isFavourite)
        storeSelectedGym(gyms[itemIndex])
        state=gyms
    }
    private fun storeSelectedGym (gym: Gym){
      val savedHandleList = stateHandle.get<List<Int>?>(FAV_IDS).orEmpty().toMutableList()
        if (gym.isFavourite) {savedHandleList.add(gym.id)}
        else {savedHandleList.remove(gym.id)}
        stateHandle[FAV_IDS]=savedHandleList
    }
    companion object{
        const val FAV_IDS="FavouriteGymIds"
    }

    private fun List<Gym>. restoreSelectedGyms(): List<Gym>{

        stateHandle.get<List<Int>?>(FAV_IDS)?.let {savedIds ->
                savedIds.forEach { gymId->
                    this.find { it.id== gymId }?.isFavourite=true
                }

            }

        return this
    }
}