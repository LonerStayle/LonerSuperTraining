package com.example.supertraining.view.dest

import android.view.View
import android.widget.Toast
import com.example.supertraining.*
import com.example.supertraining.databinding.FragmentMainBinding
import com.example.supertraining.view.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.example.supertraining.db.entity.RoomEntityTest
import com.example.supertraining.utill.toastShortShow
import com.example.supertraining.view.adapter.RecyclerViewMainAdapter

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun FragmentMainBinding.setDataBind() {
        mainViewModel.insert(
            RoomEntityTest(
                text = resources.getStringArray(R.array.testList).toList()
            )
        )
        main = this@MainFragment
        mainViewModelTest()
    }

    override fun FragmentMainBinding.setClickListener() {}


    private fun FragmentMainBinding.mainViewModelTest() {

        mainViewModel.testList.observe(viewLifecycleOwner, {
            recyclerview.adapter = RecyclerViewMainAdapter(it)
        })
    }

    fun setButtonGoToTheBroadCastTextClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_broadCastTestFragment)

    }

    fun setButtonGoToTheServiceTestClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_serviceTestFragment)

    }

    fun setButtonContentProviderClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_contentProviderTest)
    }

    fun setButtonGoToTheNaviClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_naviTestFragment)
    }

    fun setButtonGotoSearchViewTestClickListener(v:View){
        findNavController().navigate(R.id.action_mainFragment_to_searchViewTestFragment)
    }

}