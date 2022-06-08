package com.meeweel.kanban_board.util

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.R

fun Spinner.setBrands(context: Context, array: List<String>) {
    this.adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, array)
}