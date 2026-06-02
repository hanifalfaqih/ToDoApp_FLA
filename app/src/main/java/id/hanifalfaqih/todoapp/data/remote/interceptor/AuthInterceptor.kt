package id.hanifalfaqih.todoapp.data.remote.interceptor

import id.hanifalfaqih.todoapp.data.local.SessionPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionPreference: SessionPreference
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sessionPreference.getToken()

        val request = chain.request().newBuilder()

        if (!token.isNullOrEmpty()) {
            request.addHeader(
                "Authorization",
                "Bearer $token"
            )
        }

        return chain.proceed(request.build())
    }

}