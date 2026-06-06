package id.hanifalfaqih.todoapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.domain.usecase.CreateTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.DeleteTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskDetailUseCase
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.LoginUseCase
import id.hanifalfaqih.todoapp.domain.usecase.RegisterUseCase
import id.hanifalfaqih.todoapp.domain.usecase.SearchTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.UpdateTaskUseCase
import id.hanifalfaqih.todoapp.presentation.auth.login.LoginViewModel
import id.hanifalfaqih.todoapp.presentation.auth.register.RegisterViewModel
import id.hanifalfaqih.todoapp.presentation.create.CreateTaskViewModel
import id.hanifalfaqih.todoapp.presentation.detail.TaskDetailViewModel
import id.hanifalfaqih.todoapp.presentation.home.HomeViewModel
import id.hanifalfaqih.todoapp.presentation.profile.ProfileViewModel
import id.hanifalfaqih.todoapp.presentation.update.UpdateTaskViewModel

class ViewModelFactory(

    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val getTasksUseCase: GetTaskUseCase,
    private val searchTaskUseCase: SearchTaskUseCase,
    private val getTaskDetailUseCase: GetTaskDetailUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val sessionPreference: SessionPreference

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return when {

            modelClass.isAssignableFrom(
                LoginViewModel::class.java
            ) -> {

                LoginViewModel(
                    loginUseCase
                ) as T
            }

            modelClass.isAssignableFrom(
                RegisterViewModel::class.java
            ) -> {

                RegisterViewModel(
                    registerUseCase
                ) as T
            }

            modelClass.isAssignableFrom(
                HomeViewModel::class.java
            ) -> {

                HomeViewModel(
                    getTasksUseCase,
                    searchTaskUseCase
                ) as T
            }

            modelClass.isAssignableFrom(
                TaskDetailViewModel::class.java
            ) -> {

                TaskDetailViewModel(
                    getTaskDetailUseCase,
                    deleteTaskUseCase
                ) as T
            }

            modelClass.isAssignableFrom(
                CreateTaskViewModel::class.java
            ) -> {

                CreateTaskViewModel(
                    createTaskUseCase
                ) as T
            }

            modelClass.isAssignableFrom(
                UpdateTaskViewModel::class.java
            ) -> {

                UpdateTaskViewModel(
                    updateTaskUseCase
                ) as T
            }

            modelClass.isAssignableFrom(
                ProfileViewModel::class.java
            ) -> {
                ProfileViewModel(
                    sessionPreference
                ) as T
            }

            else -> {
                throw IllegalArgumentException(
                    "Unknown ViewModel Class"
                )
            }
        }
    }
}