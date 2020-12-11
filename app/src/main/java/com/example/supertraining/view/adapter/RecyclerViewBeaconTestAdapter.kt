package com.example.supertraining.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.supertraining.R
import com.example.supertraining.databinding.ViewholderBeaconTestBinding
import com.example.supertraining.view.utill.getDistance
import com.minew.beacon.BeaconValueIndex
import com.minew.beacon.MinewBeacon

class RecyclerViewBeaconTestAdapter :
    RecyclerView.Adapter<RecyclerViewBeaconTestAdapter.ViewHolder>() {

    var beaconList: MutableList<MinewBeacon> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ViewholderBeaconTestBinding>(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_beacon_test,
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            beacon = beaconList[position]

        }
    }
    override fun getItemCount() = beaconList.size
}