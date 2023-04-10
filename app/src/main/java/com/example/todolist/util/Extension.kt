package com.example.todolist.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val inputMethodService = context.getSystemService(Context.INPUT_METHOD_SERVICE)
    (inputMethodService as? InputMethodManager)?.hideSoftInputFromWindow(windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}