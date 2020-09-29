package com.example.supertraining.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.supertraining.R
import com.example.supertraining.databinding.RecyclerviewBinding
import com.example.supertraining.db.locale_db.entity.RoomEntityTest

class RecyclerViewMainAdapter(val list: List<RoomEntityTest> = listOf()) :
    RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<RecyclerviewBinding>(view)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        )

    override fun getItemCount():Int{
        return list[0].text.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            text = list[0].text[position]
        }
    }



}