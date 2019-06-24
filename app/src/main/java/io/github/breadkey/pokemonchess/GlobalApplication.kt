package io.github.breadkey.pokemonchess

import android.app.Application
import android.content.Context

class GlobalApplication: Application() {
    companion object {
        lateinit var instance: GlobalApplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun context(): Context = applicationContext
}