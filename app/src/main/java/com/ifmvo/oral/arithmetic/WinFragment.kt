package com.ifmvo.oral.arithmetic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ifmvo.oral.arithmetic.databinding.FragmentWinBinding

/**
 * A simple [Fragment] subclass.
 */
class WinFragment : Fragment() {


    private val mViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MyViewModel::class.java)
    }
    private lateinit var mBinding: FragmentWinBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.data = mViewModel
        mBinding.lifecycleOwner = requireActivity()
    }
}
