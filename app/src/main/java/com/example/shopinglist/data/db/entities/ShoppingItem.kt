package com.example.shopinglist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shopping_Table")
data class ShoppingItem(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        var amount: Int
)
