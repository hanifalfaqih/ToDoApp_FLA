package id.hanifalfaqih.todoapp.di

import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.data.remote.network.RetrofitClient
import id.hanifalfaqih.todoapp.data.repository.AuthRepository
import id.hanifalfaqih.todoapp.data.repository.AuthRepositoryImpl
import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.data.repository.TaskRepositoryImpl

object RepositoryModule {
    fun provideAuthRepository(
        retrofitClient: RetrofitClient,
        sessionPreference: SessionPreference
    ): AuthRepository {

        return AuthRepositoryImpl(
            authApi = retrofitClient.authApi,
            sessionPreference = sessionPreference
        )
    }

    fun provideTaskRepository(
        retrofitClient: RetrofitClient
    ): TaskRepository {

        return TaskRepositoryImpl(
            taskApi = retrofitClient.taskApi
        )
    }
}