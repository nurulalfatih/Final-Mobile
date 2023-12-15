package com.d121211008.brown

import android.app.Application
import com.d121211008.brown.data.AppContainer
import com.d121211008.brown.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}