package com.oyamo.hilt_android.data.di

import android.content.Context
import com.oyamo.hilt_android.data.entity.LastTime
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LastTimeModule {
    @Provides
    fun provideLastTime(@ApplicationContext ctx: Context): LastTime {
        return LastTime(ctx)
    }
}