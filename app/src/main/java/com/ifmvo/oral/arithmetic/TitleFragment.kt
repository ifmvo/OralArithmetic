package com.ifmvo.oral.arithmetic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.ifmvo.oral.arithmetic.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.*

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    private val mViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MyViewModel::class.java)
    }
    private lateinit var mBinding: FragmentTitleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.data = mViewModel
        mBinding.lifecycleOwner = requireActivity()

        button.setOnClickListener {
            val controller = Navigation.findNavController(it)
            controller.navigate(R.id.action_titleFragment_to_qustionFragment)
        }
    }
}
