package com.example.supertraining.view.dest

import androidx.fragment.app.viewModels
import com.example.supertraining.BR
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentSimpleRecyclerviewTestBinding
import com.example.supertraining.databinding.ItemTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.base.SimpleAdapter
import com.example.supertraining.view.base.TestBase
import com.example.supertraining.viewmodel.SimpleRvTestViewModel

class SimpleRecyclerViewTestFragment : TestBase<FragmentSimpleRecyclerviewTestBinding>(
    R.layout.fragment_simple_recyclerview_test,
    SimpleRvTestViewModel::class.java
) {


    override fun FragmentSimpleRecyclerviewTestBinding.setDataBind() {

        val adapter = object : SimpleAdapter.Adapter<String, ItemTestBinding>(
            R.layout.dialog_test,
            BR.currentText
        ) {}
        recyclerViewSimple.adapter = adapter
    }
}