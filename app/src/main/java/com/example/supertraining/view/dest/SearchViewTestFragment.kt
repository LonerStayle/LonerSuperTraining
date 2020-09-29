package com.example.supertraining.view.dest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentSearchViewTestBinding
import com.example.supertraining.databinding.FragmentServiceTestBinding
import com.example.supertraining.db.entity.RoomEntityTest
import com.example.supertraining.view.adapter.RecyclerViewSearchTestAdapter
import com.example.supertraining.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_view_test.*


class SearchViewTestFragment :
    BaseFragment<FragmentSearchViewTestBinding>(R.layout.fragment_search_view_test) {

    lateinit var adapter: RecyclerViewSearchTestAdapter
    private var dataList:MutableList<RoomEntityTest>? = null

    override fun FragmentSearchViewTestBinding.setDataBind() {
        thisFragment = this@SearchViewTestFragment
        setAdapter()
    }


    override fun FragmentSearchViewTestBinding.setClickListener() {
        searchViewTextChange()
    }

    private fun FragmentSearchViewTestBinding.setAdapter() {
        mainViewModel.testList.observe(viewLifecycleOwner, {
            dataList = it as MutableList<RoomEntityTest>
            adapter = RecyclerViewSearchTestAdapter(dataList!!)
            recyclerViewSearchViewTest.adapter = adapter
        })
    }

    private fun FragmentSearchViewTestBinding.searchViewTextChange() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }

    fun setSearchViewClIckListener(v:View) {
        binding.searchView.isIconified = false
    }



}