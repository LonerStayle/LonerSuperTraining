package com.example.supertraining.view.utill

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("orientation")
fun orientation(view: RecyclerView,@RecyclerView.Orientation orientation:Int){
    (view.layoutManager as LinearLayoutManager ).orientation = orientation
}