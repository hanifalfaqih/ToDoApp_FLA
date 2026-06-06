package id.hanifalfaqih.todoapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.data.repository.AuthRepository
import kotlinx.coroutines.launch
import java.io.File

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val sessionPreference: SessionPreference
) : ViewModel() {

    fun getUserName(): String? {
        return sessionPreference.getName()
    }

    fun getUserEmail(): String? {
        return "user@example.com"
    }

    fun updateProfile(name: String, email: String) {
        viewModelScope.launch {
            try {
                authRepository.updateProfile(name, email)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updatePhoto(file: File) {
        viewModelScope.launch {
            try {
                authRepository.updateProfilePhoto(file)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deletePhoto() {
        viewModelScope.launch {
            try {
                authRepository.deleteProfilePhoto()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun changePassword(old: String, new: String) {
        viewModelScope.launch {
            try {
                authRepository.changePassword(old, new)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            try {
                authRepository.deleteAccount()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun logout() {
        sessionPreference.clearSession()
    }
}