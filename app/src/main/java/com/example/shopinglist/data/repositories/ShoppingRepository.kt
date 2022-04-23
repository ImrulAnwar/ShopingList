package com.example.shopinglist.data.repositories

import androidx.lifecycle.LiveData
import com.example.shopinglist.data.db.ShoppingDatabase
import com.example.shopinglist.data.db.entities.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase) {
        val allShoppingItem: LiveData<List<ShoppingItem>> = db.getShoppingDao().getAllShoppingItem()
        suspend fun upsert(item: ShoppingItem) {
                db.getShoppingDao().upsert(item)
        }
        suspend fun delete(item: ShoppingItem) {
                db.getShoppingDao().delete(item)
        }
}