// Pega este código actualizado en tu archivo ApiClient.kt

package com.example.application.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor // <-- Import del interceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Objeto Singleton para gestionar el cliente de la API.
 */
object ApiClient {

    private const val BASE_URL = "https://api.pedalean2.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // ========= CÓDIGO SIMPLIFICADO SIN EL 'IF' =========
    /**
     * Creación del cliente OkHttpClient con el interceptor de logging.
     */
    private val okHttpClient: OkHttpClient by lazy {
        // 1. Creamos el interceptor
        val loggingInterceptor = HttpLoggingInterceptor()

        // 2. Configuramos el nivel de detalle para que SIEMPRE muestre todo.
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // 3. Construimos el cliente OkHttp añadiendo nuestro interceptor
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    // =================================================

    /**
     * Creación de la instancia de Retrofit.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient) // <-- Le pasamos el cliente con el interceptor
            .build()
    }

    /**
     * Propiedad pública para acceder al servicio de la API.
     */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
