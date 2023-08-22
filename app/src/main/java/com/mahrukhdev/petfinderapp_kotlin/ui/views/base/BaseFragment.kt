package com.mahrukhdev.petfinderapp_kotlin.ui.views.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragmentV2<VDB: ViewDataBinding>(@LayoutRes private val layoutResId : Int) :
    Fragment(){

    private var _binding: VDB? = null
    protected val binding: VDB
        get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate Method
        Log.d(BaseFragmentV2::class.simpleName, "onCreateView: ")
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        // Optionally set lifecycle owner if needed

        return binding.root
    }

}