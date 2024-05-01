package com.example.newsuktechtest.di

import android.net.Uri
import androidx.core.net.toUri
import com.example.newsuktechtest.utils.Constants.API
import com.example.newsuktechtest.utils.Constants.COINS_URI
import com.example.newsuktechtest.utils.DispatcherProvider
import com.example.newsuktechtest.utils.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl

    @Provides
    @Named(COINS_URI)
    fun provideHost(): Uri = API.toUri()
}
