package id.hanifalfaqih.todoapp.di

import android.content.Context
import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.domain.usecase.CreateTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.DeleteTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskDetailUseCase
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.LoginUseCase
import id.hanifalfaqih.todoapp.domain.usecase.RegisterUseCase
import id.hanifalfaqih.todoapp.domain.usecase.SearchTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.UpdateTaskUseCase

object AppModule {

    fun provideViewModelFactory(
        context: Context
    ): ViewModelFactory {

        val sessionPreference =
            NetworkModule.provideSessionPreference(
                context
            )

        val authInterceptor =
            NetworkModule.provideAuthInterceptor(
                sessionPreference
            )

        val okHttpClient =
            NetworkModule.provideOkHttpClient(
                authInterceptor
            )

        val retrofitClient =
            NetworkModule.provideRetrofitClient(
                okHttpClient
            )

        val authRepository =
            RepositoryModule.provideAuthRepository(
                retrofitClient,
                sessionPreference
            )

        val taskRepository =
            RepositoryModule.provideTaskRepository(
                retrofitClient
            )

        val loginUseCase =
            LoginUseCase(authRepository)

        val registerUseCase =
            RegisterUseCase(authRepository)

        val getTasksUseCase =
            GetTaskUseCase(taskRepository)

        val searchTaskUseCase =
            SearchTaskUseCase(taskRepository)

        val getTaskDetailUseCase =
            GetTaskDetailUseCase(taskRepository)

        val createTaskUseCase =
            CreateTaskUseCase(taskRepository)

        val updateTaskUseCase =
            UpdateTaskUseCase(taskRepository)

        val deleteTaskUseCase =
            DeleteTaskUseCase(taskRepository)

        return ViewModelFactory(
            loginUseCase = loginUseCase,
            registerUseCase = registerUseCase,
            getTasksUseCase = getTasksUseCase,
            searchTaskUseCase = searchTaskUseCase,
            getTaskDetailUseCase = getTaskDetailUseCase,
            createTaskUseCase = createTaskUseCase,
            updateTaskUseCase = updateTaskUseCase,
            deleteTaskUseCase = deleteTaskUseCase,
            sessionPreference = sessionPreference,
            authRepository = authRepository
        )
    }

}