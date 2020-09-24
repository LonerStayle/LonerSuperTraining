package com.example.supertraining.view.adapter

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supertraining.R
import com.example.supertraining.contentprovider.samaple.Cheese

class CheeseAdapter : RecyclerView.Adapter<CheeseAdapter.ViewHolder?>() {
    private var cursor: Cursor? = null

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(
            parent.context
        ).inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
    ) {
        val text = itemView.findViewById<TextView>(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (cursor!!.moveToPosition(position)) {
            holder.text.text =
                cursor!!.getString(cursor!!.getColumnIndexOrThrow(Cheese.COLUMN_NAME))
        }
    }

    override fun getItemCount(): Int {
        return if (cursor == null)
            0
        else
            cursor!!.count
    }

    fun setCheeses(cursor: Cursor?) {
        this.cursor = cursor
        notifyDataSetChanged()
    }


}