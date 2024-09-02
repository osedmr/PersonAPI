package com.example.personsapp.di

import com.example.personsapp.data.datasource.KisilerDataSource
import com.example.personsapp.data.repository.KisilerRepository
import com.example.personsapp.refrofit.ApiUtils
import com.example.personsapp.refrofit.KisilerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideKisilerDataSource(kdao: KisilerDao): KisilerDataSource {
        return KisilerDataSource(kdao)
    }

    @Provides
    @Singleton
    fun provideKisilerRepository(kds: KisilerDataSource): KisilerRepository {
        return KisilerRepository(kds)
    }
    @Provides
    @Singleton
    fun provideKisilerDao(): KisilerDao {
        return ApiUtils.getKisilerDao()
    }


}