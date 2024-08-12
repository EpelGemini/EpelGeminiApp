package com.epelgemini.core.di

import com.epelgemini.core.domain.dispatchers.DispatchersProvider
import com.epelgemini.core.utils.StandardDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun provideDispatchers(
        dispatchers: StandardDispatchers
    ): DispatchersProvider
    
}