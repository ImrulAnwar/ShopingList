package com.example.shopinglist.ui.shopping_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.R
import com.example.shopinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingListAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel) :
        RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {
        inner class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.shopping_item, parent, false)
                return ShoppingListViewHolder(view)
        }

        override fun getItemCount(): Int {
                return items.size
        }

        override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
                val currentItem = items[position]
                holder.itemView.tvName.text = currentItem.name
                holder.itemView.tvAmount.text = "${currentItem.amount}"
                holder.itemView.ivDelete.setOnClickListener{
                        viewModel.delete(currentItem)
                }
                holder.itemView.ivPlus.setOnClickListener{
                        currentItem.amount++
                        viewModel.upsert(currentItem)
                }
                holder.itemView.ivMinus.setOnClickListener{
                        if (currentItem.amount > 0) {
                                currentItem.amount--
                                viewModel.upsert(currentItem)
                        }
                }
        }

}