package com.mokapos.americano.dependency

import android.content.Context
import androidx.room.Room
import com.mokapos.americano.data.AmericanoDatabase
import com.mokapos.americano.data.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AmericanoDatabase {
        return Room.databaseBuilder(context, AmericanoDatabase::class.java, "Americano.db")
            .build()
    }

    @Provides
    fun provideProductDao(database: AmericanoDatabase): ProductDao {
        return database.productDao()
    }

}