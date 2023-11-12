package com.bakery.dapurclaraa.helper

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class AlertDialogHelper(private val context: Context) {

    fun showAlertDialog(
        title: String? = null,
        message: String,
        positiveButtonTitle: String = "OK",
        negativeButtonTitle: String? = null,
        positiveButtonClickListener: DialogInterface.OnClickListener? = null,
        negativeButtonClickListener: DialogInterface.OnClickListener? = null
    ) {
        val builder = AlertDialog.Builder(context)

        title?.let { builder.setTitle(it) }

        builder.setMessage(message)

        builder.setPositiveButton(positiveButtonTitle, positiveButtonClickListener)

        if (negativeButtonTitle != null) {
            builder.setNegativeButton(negativeButtonTitle, negativeButtonClickListener)
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}