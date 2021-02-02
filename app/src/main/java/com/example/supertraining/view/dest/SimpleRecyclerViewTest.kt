package com.example.supertraining.view.dest

import com.example.supertraining.BR
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentSimpleRecyclerviewTestBinding
import com.example.supertraining.databinding.ItemTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.base.SimpleAdapter
import com.example.supertraining.view.utill.toastLongShow

class SimpleRecyclerViewTest: BaseFragment<FragmentSimpleRecyclerviewTestBinding>(
    R.layout.fragment_simple_recyclerview_test
) {
    override fun FragmentSimpleRecyclerviewTestBinding.setDataBind() {
        val adapter = object :SimpleAdapter.Adapter<String,ItemTestBinding>(
            R.layout.dialog_test,
            BR.simpleText,
            {requireContext().toastLongShow("테스트!")}
        ) {}

        adapter.replaceAll(
            listOf(
                "하이하이하이", "하이하이하이", "하이하이하이", "하이하이하이", "하이하이하이",
                "하이하이하이", "하이하이하이", "하이하이하이", "하이하이하이",
            )
        )
    }
}