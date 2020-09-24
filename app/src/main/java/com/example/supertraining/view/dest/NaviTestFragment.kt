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
        setBackButtonSetting()
    }


    override fun FragmentNaviTestBinding.setClickListener() {

    }


    @SuppressLint("RestrictedApi")
    private fun FragmentNaviTestBinding.setBackButtonSetting() {
        requireActivity().onBackPressedDispatcher.addCallback {
            if (findNavController().backStack.last == null) {
                Snackbar.make(root, "네비 컨트롤러 ", Snackbar.LENGTH_SHORT).show()
            } else {
                requireActivity().onBackPressed()
                Snackbar.make(root, "뒤로 가기", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

}