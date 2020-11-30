package com.example.supertraining.view.dest

import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentSearchViewTestBinding
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.db.locale_db.entity.RoomEntityTest
import com.example.supertraining.view.adapter.RecyclerViewSearchTestAdapter
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.viewmodel.TestViewModel
import com.example.supertraining.viewmodel.factory.ViewModelFactory


class SearchViewTestFragment :
    BaseFragment<FragmentSearchViewTestBinding>(R.layout.fragment_search_view_test) {

    private val mainViewModel by viewModels<TestViewModel> {
        val testDatabase = TestDataBase.getInstance(requireContext())
        val factory = ViewModelFactory(testDatabase.dataSource)
        factory
    }

    lateinit var adapter: RecyclerViewSearchTestAdapter
    private var dataList: MutableList<RoomEntityTest>? = null

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

        }
        )
    }

    fun setSearchViewClIckListener(v: View) {
        binding.searchView.isIconified = false
    }


}