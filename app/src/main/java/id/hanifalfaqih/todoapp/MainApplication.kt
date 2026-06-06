package id.hanifalfaqih.todoapp

import android.app.Application

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {

        private lateinit var instance: MainApplication

        fun getInstance(): MainApplication {
            return instance
        }
    }

}