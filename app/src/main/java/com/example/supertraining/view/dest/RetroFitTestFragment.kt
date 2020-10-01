package com.example.supertraining.view.dest

import android.util.Log
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentRetrofitTestBinding
import com.example.supertraining.db.network_db.dataholder.User
import com.example.supertraining.view.base.BaseFragment
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroFitTestFragment :
    BaseFragment<FragmentRetrofitTestBinding>(R.layout.fragment_retrofit_test) {
    override fun FragmentRetrofitTestBinding.setDataBind() {
        setData()
        setObserver()

    }

    override fun FragmentRetrofitTestBinding.setClickListener() {}


    private fun FragmentRetrofitTestBinding.setData() {
        thisFragment = this@RetroFitTestFragment
        vm = networkViewModel

    }


    private fun FragmentRetrofitTestBinding.setObserver() {
        networkViewModel.userData.observe(viewLifecycleOwner, {
            textViewUserDataCheck.text =
                getString(R.string.retrofitTest_textUserData, it.userId, it.email)
        })
    }


    val jsonCallback = object : Callback<JsonElement> {
        override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
            Log.d("registerCheck", "회원 가입 성공 ")
        }

        override fun onFailure(call: Call<JsonElement>, t: Throwable) {
            Log.d("registerCheck", "네트워크 문제로 회원 가입 실패 ")
        }
    }

     fun setUserData():User{
        binding.apply {
           return User(
                editTextIdSignUp.text.toString(),
                editTextPasswordSignUp.text.toString(),
                editTextEmailSignUp.text.toString()
            )
        }
    }
}