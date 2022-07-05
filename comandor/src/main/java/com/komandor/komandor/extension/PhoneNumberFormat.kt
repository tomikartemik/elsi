package com.komandor.komandor.extension


import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatEditText

import androidx.lifecycle.MutableLiveData
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

fun AppCompatEditText.phoneNumberFormat(liveData: MutableLiveData<String>) {
    val affineFormats: MutableList<String> = ArrayList()
    //affineFormats.add("[0] ([000]) [000] [00] [00]")

    MaskedTextChangedListener.installOn(
        this,
        "+[0] ([000]) [000] [00] [00]",
        affineFormats, AffinityCalculationStrategy.PREFIX,
        object : MaskedTextChangedListener.ValueListener {
            override fun onTextChanged(
                maskFilled: Boolean,
                @NonNull extractedValue: String,
                @NonNull formattedText: String
            ) {
                val r= extractedValue.replace("(", "").replace(")", "").trim()
                //L.d("phoneNumberFormat", "extractedValue = "+extractedValue+ " r = "+r+" r.l = "+r.length)
                liveData.value = r
            }
        }
    )
}