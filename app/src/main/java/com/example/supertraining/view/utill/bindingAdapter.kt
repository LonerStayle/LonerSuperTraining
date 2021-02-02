package com.example.supertraining.view.utill

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supertraining.view.base.SimpleAdapter

@BindingAdapter("app:orientation")
fun orientation(view: RecyclerView, @RecyclerView.Orientation orientation: Int) {
    (view.layoutManager as LinearLayoutManager).orientation = orientation
}

@BindingAdapter("app:replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) =
    (this.adapter as SimpleAdapter.Adapter<Any, *>)?.run {
        this.replaceAll(list)
        notifyDataSetChanged()
    }

