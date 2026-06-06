package id.hanifalfaqih.todoapp.data.repository

import java.io.File

interface AuthRepository {
    suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): String

    suspend fun login(
        username: String,
        password: String
    ): String

    suspend fun updateProfile(name: String, email: String): String
    suspend fun updateProfilePhoto(photo: File): String
    suspend fun deleteProfilePhoto(): String
    suspend fun changePassword(oldPass: String, newPass: String): String
    suspend fun deleteAccount(): String
}