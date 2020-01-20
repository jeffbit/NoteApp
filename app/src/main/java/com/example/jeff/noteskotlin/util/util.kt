package com.example.jeff.noteskotlin.util

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

const val DETAIL_NOTE_TITLE = "DETAIL NOTE TITLE"
const val DETAIL_NOTE_DESC = "DETAIL NOTE DESC"
const val DETAIL_NOTE_COMPLETE = "DETAIL NOTE COMPLETED"
const val ADD_NOTE_TITLE = "ADD NOTE TITLE"
const val ADD_NOTE_DESC = "ADD NOTE DESC"


fun convertDateToString(date: String): String {
    val month_date = SimpleDateFormat("MM yyyy", Locale.ENGLISH)
    val formatedDate = month_date.format(date)
    return formatedDate

}

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
