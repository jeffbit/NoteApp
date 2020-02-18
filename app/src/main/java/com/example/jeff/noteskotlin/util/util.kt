package com.example.jeff.noteskotlin.util

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment



fun AppCompatActivity.closeKeyBoard() {
    val view = this.currentFocus
    if (view != null) {
        val iim = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        iim.hideSoftInputFromWindow(view.windowToken, 0)

    } else {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}

fun Fragment.hideKeyBoard() {
    val activity = this.activity
    if (activity is AppCompatActivity) {
        activity.closeKeyBoard()
    }
}





