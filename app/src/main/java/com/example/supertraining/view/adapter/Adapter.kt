package com.example.supertraining.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.supertraining.R
import com.example.supertraining.databinding.RecyclerviewBinding
import com.example.supertraining.db.entity.RoomEntityTest

class Adapter(val list: List<RoomEntityTest> = listOf(), val clickEvent: (String) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<RecyclerviewBinding>(view)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            text = list[0].text[0]
            adapter = this@Adapter
        }
    }

    //성공한 방법
    fun clickEvent() {
        clickEvent(list[0].text[0])
    }

}