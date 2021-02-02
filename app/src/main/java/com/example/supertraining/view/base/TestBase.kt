package com.example.supertraining.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.supertraining.BR
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.viewmodel.factory.ViewModelFactory

abstract class TestBase<VDB : ViewDataBinding>(
    @LayoutRes val layoutRes: Int,
    protected val viewModelCls: Class<out ViewModel>,
) : Fragment() {

    lateinit var binding: VDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(layoutInflater, layoutRes, container, false).run {
        lifecycleOwner = this@TestBase
        binding = this
        getBinding {
            TestDataBase.getInstance(requireContext()).also {
                setVariable(
                    BR.vm,
                    ViewModelProvider(
                        this@TestBase,
                        ViewModelFactory(it.dataSource)
                    )[viewModelCls]
                )
            }
        }
        setDataBind()
        setClickListener()
        root
    }

    protected fun getBinding(action: VDB.() -> Unit) {
        binding.run(action)
    }

    abstract fun VDB.setDataBind()
    open fun VDB.setClickListener() = Unit
}