package com.example.shopinglist.ui.shopping_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglist.data.db.ShoppingDatabase
import com.example.shopinglist.data.db.entities.ShoppingItem
import com.example.shopinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(application: Application) : AndroidViewModel(application) {
        private val repository: ShoppingRepository = ShoppingRepository(ShoppingDatabase.getDatabase(application))
        fun upsert(item: ShoppingItem) {
                viewModelScope.launch(Dispatchers.IO)  {
                        repository.upsert(item)
                }
        }
        fun delete(item: ShoppingItem) {
                viewModelScope.launch (Dispatchers.IO){
                        repository.delete(item)
                }
        }
        fun getAllShoppingItem(): LiveData<List<ShoppingItem>> {
                return repository.getAllShoppingItem()
        }
}