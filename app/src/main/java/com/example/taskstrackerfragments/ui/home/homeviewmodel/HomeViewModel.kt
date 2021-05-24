package com.example.taskstrackerfragments.ui.home.homeviewmodel

import androidx.lifecycle.ViewModel
import com.example.taskstrackerfragments.dagger.HabitComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val applicationComponent: HabitComponent): ViewModel() {

    init {
        GlobalScope.launch {
            applicationComponent.getFillDataBaseUseCase().fillDataBase()
        }
    }
}