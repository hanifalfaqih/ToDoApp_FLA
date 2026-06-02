package id.hanifalfaqih.todoapp.data.repository

interface AuthRepository {
    suspend fun register(
        name: String,
        username: String,
        password: String
    ): String

    suspend fun login(
        username: String,
        password: String
    ): String
}