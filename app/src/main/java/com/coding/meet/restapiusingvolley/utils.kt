package com.coding.meet.restapiusingvolley

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View


fun SpannableString.withClickableSpan(
    clickablePart : String,
    color : Int,
    isUnderLineText : Boolean = true,
    onClickListener: () -> Unit
):SpannableString{
    val clickableSpan = object : ClickableSpan(){
        override fun onClick(widget: View) {
            onClickListener.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)

            //here TextView COlor
            ds.color = color
            // here TextView Underline
            ds.isUnderlineText = isUnderLineText
        }
    }
    val clickablePartStart = indexOf(clickablePart)
    setSpan(
        clickableSpan,
        clickablePartStart,
        clickablePartStart + clickablePart.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}