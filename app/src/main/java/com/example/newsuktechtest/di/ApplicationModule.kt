package com.example.newsuktechtest.di

import android.app.Application
import android.content.Context
import android.net.Uri
import com.example.newsuktechtest.data.api.CoinsService
import com.example.newsuktechtest.utils.Constants.API
import com.example.newsuktechtest.utils.Constants.API_TIMEOUT
import com.example.newsuktechtest.utils.Constants.CACHE_SIZE
import com.example.newsuktechtest.utils.Constants.COINS_CLIENT
import com.example.newsuktechtest.utils.Constants.COINS_URI
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesBaseUrl() = API

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Named(COINS_CLIENT)
    fun providesRetrofit(
        @Named(COINS_URI) baseUri: Uri,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUri.toString().toHttpUrl())
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()

    @Provides
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        cache:Cache
    ) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        .callTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        .cache(cache)
        .build()

    @Provides
    fun providesMoshi() : Moshi = Moshi
        .Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesAPiService(@Named(COINS_CLIENT) retrofit: Retrofit) : CoinsService = retrofit.create(CoinsService::class.java)

}
