package com.coding.meet.restapiusingvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.coding.meet.restapiusingvolley.models.Product
import com.coding.meet.restapiusingvolley.models.Rating

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
         val productTitleTxt: TextView = findViewById(R.id.productTitleTxt)
         val productPriceTxt: TextView = findViewById(R.id.productPriceTxt)
         val productRatingTxt: TextView = findViewById(R.id.productRatingTxt)
         val productReviewTxt: TextView = findViewById(R.id.productReviewTxt)
         val productImage: ImageView = findViewById(R.id.productImage)

        val product = Product(
            intent.getIntExtra("id",0),
            intent.getStringExtra("title")!!,
            intent.getStringExtra("image")!!,
            Rating(
            intent.getIntExtra("count",0),
            intent.getDoubleExtra("rate",0.0),
            ),
            intent.getDoubleExtra("price",0.0),
        )

        Glide.with(productImage)
            .load(product.image)
            .into(productImage)

        productTitleTxt.text = product.title
        productPriceTxt.text = "USD ${product.price}"
        productRatingTxt.text = product.rating.rate.toString()
        productReviewTxt.text = "${product.rating.count} Reviews"
    }
}