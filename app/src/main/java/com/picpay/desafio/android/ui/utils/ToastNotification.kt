package com.picpay.desafio.android.ui.utils

import android.content.Context
import android.widget.Toast

class ToastNotification {

    fun notification(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

}