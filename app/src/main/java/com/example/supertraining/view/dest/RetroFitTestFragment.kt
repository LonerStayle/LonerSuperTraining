package com.example.supertraining.view.dest

import android.annotation.SuppressLint
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentRetrofitTestBinding
import com.example.supertraining.db.network_db.dataholder.User
import com.example.supertraining.view.base.BaseFragment

class RetroFitTestFragment:BaseFragment<FragmentRetrofitTestBinding>(R.layout.fragment_retrofit_test) {

    override fun FragmentRetrofitTestBinding.setDataBind() {
        thisFragment = this@RetroFitTestFragment
        vm = networkViewModel
        user = User("hihihi","hihihi","hihihi")

        networkViewModel.userData.observe(viewLifecycleOwner,{
            textViewUserDataCheck.text = getString(R.string.retrofitTest_textUserData,it.userId,it.email)
        })

    }

    override fun FragmentRetrofitTestBinding.setClickListener() {}
}