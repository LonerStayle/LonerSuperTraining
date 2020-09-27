package com.example.supertraining.view.dest

import android.annotation.SuppressLint
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.supertraining.*
import com.example.supertraining.databinding.FragmentNaviTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

class NaviTestFragment : BaseFragment<FragmentNaviTestBinding>(R.layout.fragment_navi_test) {

    override fun FragmentNaviTestBinding.setDataBind() {
        setSystemBackButtonSetting()
    }


    override fun FragmentNaviTestBinding.setClickListener() {}


    @SuppressLint("RestrictedApi")
    private fun setSystemBackButtonSetting() {
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().onBackPressed()
        }
    }
}