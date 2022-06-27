package com.example.challangereadness

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.challangereadness.viewModel.MainViewModel

class CategoryListener(
    private val context: Context,
    private val categories: List<String>,
    private val viewModel: MainViewModel
) : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setCategory(categories[position])
        Toast.makeText(context, categories[position], Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

}