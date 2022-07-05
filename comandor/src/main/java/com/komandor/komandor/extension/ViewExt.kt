package com.komandor.komandor.extension

import android.widget.ImageView
import com.komandor.komandor.R
import com.squareup.picasso.Picasso

fun ImageView.load(url:String){
    Picasso.get().load(url)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.account_circle)
        .error(R.drawable.account_circle)
        .into(this);
}