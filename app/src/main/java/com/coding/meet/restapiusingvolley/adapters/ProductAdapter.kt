package com.coding.meet.restapiusingvolley.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.meet.restapiusingvolley.R
import com.coding.meet.restapiusingvolley.models.Product

class ProductAdapter(
    val onClickListener : (Product) -> Unit
) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productTitleTxt: TextView = itemView.findViewById(R.id.productTitleTxt)
        private val productPriceTxt: TextView = itemView.findViewById(R.id.productPriceTxt)
        private val productRatingTxt: TextView = itemView.findViewById(R.id.productRatingTxt)
        private val productReviewTxt: TextView = itemView.findViewById(R.id.productReviewTxt)
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)

        fun bindData(product: Product) {
            Glide.with(productImage)
                .load(product.image)
                .into(productImage)

            productTitleTxt.text = product.title
            productPriceTxt.text = "USD ${product.price}"
            productRatingTxt.text = product.rating.rate.toString()
            productReviewTxt.text = "${product.rating.count} Reviews"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.bindData(product)
        holder.itemView.setOnClickListener {
            onClickListener(product)
        }
    }
}