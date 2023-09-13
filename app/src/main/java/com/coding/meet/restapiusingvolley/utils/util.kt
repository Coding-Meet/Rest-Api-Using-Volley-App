package com.coding.meet.restapiusingvolley.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(view:ImageView,url:String){
    Glide.with(view)
        .load(url)
        .into(view)

}