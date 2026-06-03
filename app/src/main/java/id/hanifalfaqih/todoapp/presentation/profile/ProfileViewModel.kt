package id.hanifalfaqih.todoapp.presentation.profile

import androidx.lifecycle.ViewModel
import id.hanifalfaqih.todoapp.data.local.SessionPreference

class ProfileViewModel(
    private val sessionPreference: SessionPreference
) : ViewModel() {

    fun getUserName(): String? {
        return sessionPreference.getName()
    }

    fun getUserEmail(): String? {
        // Since the API doesn't return email in login, we might not have it unless we save it during register.
        // Let's assume we save it or just use a placeholder.
        return "user@example.com"
    }

    fun logout() {
        sessionPreference.clearSession()
    }
}