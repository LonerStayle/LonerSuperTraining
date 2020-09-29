package com.example.supertraining.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.supertraining.R
import com.example.supertraining.databinding.RecyclerviewBinding
import com.example.supertraining.db.entity.RoomEntityTest

class RecyclerViewSearchTestAdapter(
    var list: MutableList<RoomEntityTest>
) :
    RecyclerView.Adapter<RecyclerViewSearchTestAdapter.ViewHolder>(), Filterable {

    var filteredList = mutableListOf<String>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<RecyclerviewBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        )

    override fun getItemCount() = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            text = filteredList[position]
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            @SuppressLint("DefaultLocale")
            override fun performFiltering(charSequence: CharSequence): FilterResults {


                val charString = charSequence.toString()

                if (charString.isEmpty()) {
                    Log.d("check", "아무것도 없을때 여기 지나감 ")
                    filteredList.clear()

                } else {
                    for (newList in list[0].text) {
                        if (newList.toLowerCase().contains(charString.toLowerCase()))
                            filteredList.add(newList)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if(filterResults.values == null)
                    return

                filteredList = filterResults.values as MutableList<String>
                notifyDataSetChanged()
            }
        }
    }


}

