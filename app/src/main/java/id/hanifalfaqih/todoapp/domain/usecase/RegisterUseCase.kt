package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        name: String,
        username: String,
        email: String,
        password: String
    ): String {

        return authRepository.register(
            name = name,
            username = username,
            email = email,
            password = password
        )
    }
}