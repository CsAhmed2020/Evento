package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evento.DataStoreUtils
import kotlinx.coroutines.launch


class EventoViewModel(
    ):ViewModel() {

        fun updateSessionState(key:String,value:Boolean){
            viewModelScope.launch {
                DataStoreUtils.savePreference(key = key, value = value)
            }
        }

    fun readSessionState(key:String):Boolean{
        return DataStoreUtils.readPreference(key = key, defaultValue = false)
    }

}