package com.example.supertraining.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleAdapter {
    abstract class Adapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes val layoutResId: Int,
        private val bindingVariableId: Int? = null,
        val clickEvent:(View)-> Unit
    ) : RecyclerView.Adapter<ViewHolder<B>>() {
        private val items = mutableListOf<ITEM>()
        fun replaceAll(items: List<ITEM>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> =
            object : ViewHolder<B>(
                layoutResId = layoutResId,
                parent = parent,
                bindingVariableId = bindingVariableId,
                clickEvent = clickEvent
            ) {}

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.onBindViewHolder(items[position])
        }

    }

    abstract class ViewHolder<B : ViewDataBinding>(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup,
        val bindingVariableId: Int?,
        val clickEvent:(View)-> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    ) {
        protected val binding: B = DataBindingUtil.bind(itemView)!!
        fun onBindViewHolder(item: Any?) {
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)

                    binding.root.setOnClickListener { view ->
                        clickEvent(view)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}


