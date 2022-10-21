package br.com.mobile.sauceburguer

import android.app.Application

class SauceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }


    companion object {
        private var appInstance: SauceApplication? = null

        fun getInstance(): SauceApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no manifest")
            }

            return appInstance!!
        }

    }
}