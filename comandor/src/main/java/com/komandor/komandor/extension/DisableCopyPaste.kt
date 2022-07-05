package com.komandor.komandor.extension

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.disableCopyPaste() {
    customSelectionActionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu): Boolean {
            return false
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
    isLongClickable = false
    setTextIsSelectable(false)
}