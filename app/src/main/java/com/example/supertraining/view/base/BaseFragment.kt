package com.example.supertraining.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.viewmodel.NetworkViewModel
import com.example.supertraining.viewmodel.TestViewModel
import com.example.supertraining.viewmodel.TestViewModelFactory

abstract class BaseFragment<VDB:ViewDataBinding>(@LayoutRes val layoutRes:Int): Fragment() {

    protected val mainViewModel by viewModels<TestViewModel> {
        val testDatabase = TestDataBase.getInstance(requireContext())
        val factory = TestViewModelFactory(testDatabase.dataSource)
        factory
    }
    protected val networkViewModel by viewModels<NetworkViewModel>()

    lateinit var binding:VDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(layoutInflater,layoutRes,container,false).run {

        lifecycleOwner = viewLifecycleOwner
        binding = this
        setDataBind()
        setClickListener()
        root
    }
    abstract fun VDB.setDataBind()
    open fun VDB.setClickListener() = Unit
}