package com.example.instagram.util

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard(view: View? = activity?.window?.decorView?.rootView) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        view?.hideKeyboard(view)
    } else {
        inputMethodManager()?.hideSoftInputFromWindow(view?.applicationWindowToken, 0)
    }
}

fun Fragment.inputMethodManager() =
    context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager