package com.komandor.komandor.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class WarningDialog(
    private val msg: String,
    private val callerOk: () -> Unit = {}
) : DialogFragment() {

    private val TAG = WarningDialog::class.java.simpleName

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        builder.setMessage(msg)
            .setPositiveButton("Продолжить") { dialog, id ->
                dialog.dismiss()
                callerOk()
            }
        return builder.create()
    }
}