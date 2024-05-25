package com.rita.calculatorapp

import android.content.Context
import android.widget.Toast

fun Context.showToast() {
    Toast.makeText(
        this,
        getString(R.string.max_digits_number_exceeded),
        Toast.LENGTH_SHORT
    ).show()
}
