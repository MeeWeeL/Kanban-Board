package com.meeweel.kanban_board.util

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.R

fun Spinner.setBrands(context: Context, array: List<String>) {
    this.adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, array)
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}