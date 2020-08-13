package com.allen.migo.di

import com.allen.migo.data.StatusRemoteDataSource
import com.allen.migo.data.StatusRepository
import com.allen.migo.network.core.NetworkHandle
import com.allen.migo.network.api.StatusService
import com.allen.migo.viewmodel.StatusViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    viewModel {
        StatusViewModel(get())
    }
}

val repositoryModule = module {
    factory { StatusRepository(get()) }
    factory { StatusRemoteDataSource(get(), get()) }
}

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideStatusService(get()) }
    single { NetworkHandle(get()) }
}

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl("http://code-test.migoinc-dev.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideStatusService(retrofit: Retrofit): StatusService =
    retrofit.create(StatusService::class.java)

val appModule = listOf(networkModule, repositoryModule, viewModelModule)