package id.hanifalfaqih.todoapp.di

import android.content.Context
import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.data.remote.interceptor.AuthInterceptor
import id.hanifalfaqih.todoapp.data.remote.network.RetrofitClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun provideSessionPreference(
        context: Context
    ): SessionPreference {

        return SessionPreference(context)
    }

    fun provideAuthInterceptor(
        sessionPreference: SessionPreference
    ): AuthInterceptor {

        return AuthInterceptor(sessionPreference)
    }

    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {

        val loggingInterceptor =
            HttpLoggingInterceptor().apply {

                level = HttpLoggingInterceptor.Level.BODY
            }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): RetrofitClient {

        return RetrofitClient(okHttpClient)
    }
}