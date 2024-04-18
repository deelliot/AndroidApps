package com.delliott.whatscooking.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1)

abstract class RecipeDatabase: RoomDatabase() {
    abstract fun dao(): RecipeDao
}