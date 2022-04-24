package com.example.shopinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopinglist.R
import com.example.shopinglist.data.db.entities.ShoppingItem
import com.example.shopinglist.ui.shopping_list.ShoppingListAdapter
import com.example.shopinglist.ui.shopping_list.ShoppingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_shopping_dialogue.view.*

class MainActivity : AppCompatActivity() {
        private lateinit var mViewModel: ShoppingViewModel
        private lateinit var dialogView: View
        private lateinit var dialog: AlertDialog
        private lateinit var adapter: ShoppingListAdapter
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                mViewModel = ViewModelProvider(this)[ShoppingViewModel::class.java]
                adapter = ShoppingListAdapter(listOf(), mViewModel)
                rvShoppingList.layoutManager = LinearLayoutManager(this)
                rvShoppingList.adapter = adapter
                fabAdd.setOnClickListener {
                        popAlertDialog()
                }

                mViewModel.getAllShoppingItem().observe(this, Observer {
                        adapter.setData(it)
                })
        }

        private fun popAlertDialog() {
                dialogView = View.inflate(
                        applicationContext,
                        R.layout.add_shopping_dialogue,
                        null
                )
                dialog = AlertDialog.Builder(this).setView(dialogView).create()
                dialog.show()
                dialogView.btnCancel.setOnClickListener {
                        dialog.cancel()
                }
                dialogView.btnAdd.setOnClickListener {
                        val name = dialogView.etName.text.toString()
                        val amount = dialogView.etAmount.text.toString()
                        if (name.isEmpty() || amount.isEmpty()) {
                                Toast.makeText(
                                        applicationContext,
                                        "Enter all the fields",
                                        Toast.LENGTH_SHORT
                                ).show()
                                return@setOnClickListener
                        }
                        val item = ShoppingItem(0, name = name, amount = amount.toInt())
                        mViewModel.upsert(item)
                        Toast.makeText(
                                applicationContext,
                                "Inserted Successfully",
                                Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                }
        }
}