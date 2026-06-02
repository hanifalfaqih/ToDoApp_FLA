package id.hanifalfaqih.todoapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.hanifalfaqih.todoapp.domain.usecase.*
import id.hanifalfaqih.todoapp.presentation.auth.login.LoginViewModel
import id.hanifalfaqih.todoapp.presentation.auth.register.RegisterViewModel
import id.hanifalfaqih.todoapp.presentation.home.HomeViewModel
import id.hanifalfaqih.todoapp.presentation.create.CreateTaskViewModel
import id.hanifalfaqih.todoapp.presentation.detail.TaskDetailViewModel
import id.hanifalfaqih.todoapp.presentation.profile.ProfileViewModel
import id.hanifalfaqih.todoapp.presentation.update.UpdateTaskViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val sessionPreference = NetworkModule.provideSessionPreference(context)
        val authInterceptor = NetworkModule.provideAuthInterceptor(sessionPreference)
        val okHttpClient = NetworkModule.provideOkHttpClient(authInterceptor)
        val retrofitClient = NetworkModule.provideRetrofitClient(okHttpClient)
        
        val authRepository = RepositoryModule.provideAuthRepository(retrofitClient, sessionPreference)
        val taskRepository = RepositoryModule.provideTaskRepository(retrofitClient)

        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(LoginUseCase(authRepository)) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(RegisterUseCase(authRepository)) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(
                    GetTaskUseCase(taskRepository),
                    SearchTaskUseCase(taskRepository)
                ) as T
            }
            modelClass.isAssignableFrom(CreateTaskViewModel::class.java) -> {
                CreateTaskViewModel(CreateTaskUseCase(taskRepository)) as T
            }
            modelClass.isAssignableFrom(TaskDetailViewModel::class.java) -> {
                TaskDetailViewModel(
                    GetTaskDetailUseCase(taskRepository),
                    DeleteTaskUseCase(taskRepository)
                ) as T
            }
            modelClass.isAssignableFrom(UpdateTaskViewModel::class.java) -> {
                UpdateTaskViewModel(UpdateTaskUseCase(taskRepository)) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(sessionPreference) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}