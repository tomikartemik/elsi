package com.komandor.komandor.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.widget.ImageView
import com.komandor.komandor.R
import com.squareup.picasso.Picasso
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

fun Bitmap.toBase64():String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun String.imageLoad():Bitmap =
    Picasso.get().load(this)
        .placeholder(R.drawable.account_circle)
        .error(R.drawable.account_circle)
        .get()



fun Uri.toBitmap(context: Context): Bitmap? {
    //L.d(TAG, " uri.path = ${uri.path}")
    val inputStream: InputStream? =
        context.contentResolver.openInputStream(this)
    //L.d(TAG, " inputStream = ${inputStream}")
    if (inputStream!=null) {
        var bufferedInputStream = BufferedInputStream(inputStream);

        var bitmap = BitmapFactory.decodeStream(bufferedInputStream);
        return bitmap
    } else {
        return null
    }
}