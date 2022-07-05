package com.komandor.komandor.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmDialog(
    private val msg: String,
    private val callerOk: () -> Unit = {},
    private val callerCancel: () -> Unit = {}
) : DialogFragment() {

    private val TAG = ConfirmDialog::class.java.simpleName

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        builder.setMessage(msg)
            .setPositiveButton("Да") { dialog, id ->
                callerOk()
                dialog.dismiss()
            }
            .setNegativeButton("Нет") { dialog, id ->
                callerCancel()
                dialog.dismiss()
            }
        return builder.create()
    }
}