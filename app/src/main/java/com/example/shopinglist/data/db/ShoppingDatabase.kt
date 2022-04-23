package com.example.shopinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopinglist.data.db.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1,)
abstract class ShoppingDatabase : RoomDatabase(){
        abstract fun getShoppingDao(): ShoppingDao

        companion object {
                @Volatile
                private var INSTANCE: ShoppingDatabase? = null
                fun getDatabase(context: Context): ShoppingDatabase {
                        synchronized(this) {
                                if (INSTANCE == null) {
                                        INSTANCE = Room.databaseBuilder(
                                                context.applicationContext,
                                                ShoppingDatabase::class.java,
                                                "Shopping Database"
                                        ).build()
                                }
                        }
                        return INSTANCE!!
                }

        }
}