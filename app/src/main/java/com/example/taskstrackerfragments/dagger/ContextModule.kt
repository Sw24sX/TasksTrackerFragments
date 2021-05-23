package com.example.taskstrackerfragments.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlin.reflect.KClass

@Module
class ContextModule(var context: Context){

    @Provides
    fun context(): Context {
        return context.applicationContext
    }

}