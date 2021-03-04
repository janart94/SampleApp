package com.example.movie.di

import com.example.movie.network.MovieClient
import com.example.movie.repository.DetailRepository
import com.example.movie.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
            movieClient: MovieClient
    ): MainRepository {
        return MainRepository(movieClient)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailRepository(
            movieClient: MovieClient): DetailRepository {
        return DetailRepository(movieClient)
    }
}
