package com.example.supertraining.view.dest

import android.view.View
import android.widget.Toast
import com.example.supertraining.*
import com.example.supertraining.databinding.FragmentMainBinding
import com.example.supertraining.db.entity.RoomEntityTest
import com.example.supertraining.view.base.BaseFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.supertraining.view.adapter.Adapter

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun FragmentMainBinding.setDataBind() {
        main = this@MainFragment
        mainViewModelTest()
    }

    override fun FragmentMainBinding.setClickListener() {}


    private fun FragmentMainBinding.mainViewModelTest() {

        mainViewModel.insert(
            RoomEntityTest(
                text = resources.getStringArray(R.array.testList).toList()
            )
        )

        mainViewModel.testList.observe(viewLifecycleOwner, Observer {
            recyclerview.adapter = Adapter(it) {
                Toast.makeText(requireContext(), "데바로 실행 가동완료", Toast.LENGTH_SHORT).show()
            }
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
}