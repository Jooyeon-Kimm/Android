package com.app.eroum.shufflepwapp.bindingadapter


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.eroum.shufflepwapp.R

@BindingAdapter(value = ["code_text", "code_index"])
fun ImageView.setPin(codeText: CharSequence?, index: Int) {
    if (codeText != null) {
        if (codeText.length > index) {
            setImageResource(R.drawable.ic_baseline_circle_black)
        } else {
            setImageResource(R.drawable.ic_baseline_circle_gray)
        }
    }
}