package com.coding.meet.restapiusingvolley

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.toSpannable
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


        val spannableTxt = findViewById<TextView>(R.id.spannableTxt)
        val strSpannable = spannableTxt.text.toSpannable() as SpannableString
        strSpannable.withClickableSpan(
            "Terms of Use",
            Color.BLUE,
            true,
            onClickListener = {
                // here Url pass
                val termUrl = Uri.parse("https://github.com/Coding-Meet")
                startActivity(Intent(Intent.ACTION_VIEW,termUrl))
            }
        )
        strSpannable.withClickableSpan(
            "Privacy Policy",
            Color.BLUE,
            true,
            onClickListener = {
                // here Url pass
                val privacyUrl = Uri.parse("https://www.youtube.com/@CodingMeet26")
                startActivity(Intent(Intent.ACTION_VIEW,privacyUrl))
            }
        )
        spannableTxt.text = strSpannable
        spannableTxt.movementMethod = LinkMovementMethod.getInstance()
        spannableTxt.highlightColor = Color.TRANSPARENT

    }
}