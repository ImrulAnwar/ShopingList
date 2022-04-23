package com.example.shopinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.shopinglist.R
import com.example.shopinglist.ui.shopping_list.ShoppingViewModel

class MainActivity : AppCompatActivity() {
        private lateinit var mViewModel: ShoppingViewModel
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                mViewModel = ViewModelProvider(this)[ShoppingViewModel::class.java]

        }
}