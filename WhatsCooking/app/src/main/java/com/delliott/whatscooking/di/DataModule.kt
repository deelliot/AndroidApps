package com.delliott.whatscooking.di

import android.app.Application
import androidx.room.Room
import com.delliott.whatscooking.MyApp
import com.delliott.whatscooking.dao.RecipeDao
import com.delliott.whatscooking.dao.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesRecipeDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            RecipeDatabase::class.java,
            "recipe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideApp(application: Application): MyApp {
        return application as MyApp
    }

    @Provides
    @Singleton
    fun provideRecipeDao(recipeDatabase: RecipeDatabase) : RecipeDao = recipeDatabase.dao()
}