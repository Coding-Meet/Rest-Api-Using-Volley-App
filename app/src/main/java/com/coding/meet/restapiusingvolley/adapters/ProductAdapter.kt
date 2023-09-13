package com.coding.meet.restapiusingvolley.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.meet.restapiusingvolley.R
import com.coding.meet.restapiusingvolley.databinding.ViewProductBinding
import com.coding.meet.restapiusingvolley.models.Product

class ProductAdapter :
    ListAdapter<Product, ProductAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(private val viewProductBinding: ViewProductBinding) :
        RecyclerView.ViewHolder(viewProductBinding.root) {
        fun bindData(product: Product) {
            viewProductBinding.product = product
            viewProductBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.bindData(product)
    }
}