package com.ifmvo.oral.arithmetic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.ifmvo.oral.arithmetic.databinding.FragmentQustionBinding
import kotlinx.android.synthetic.main.fragment_qustion.*

/**
 * A simple [Fragment] subclass.
 */
class QustionFragment : Fragment() {

    private var answerSb = StringBuilder()

    private val mViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MyViewModel::class.java)
    }
    private lateinit var mBinding: FragmentQustionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_qustion, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.data = mViewModel
        mBinding.lifecycleOwner = requireActivity()

        mViewModel.generate()

        val onClickListener = View.OnClickListener {

            if (answerSb.length >= 2) {
                return@OnClickListener
            }

            it as Button
            val number = it.text.toString()
            answerSb.append(number)
            textView9.text = answerSb
        }

        button0.setOnClickListener(onClickListener)
        button1.setOnClickListener(onClickListener)
        button2.setOnClickListener(onClickListener)
        button3.setOnClickListener(onClickListener)
        button4.setOnClickListener(onClickListener)
        button5.setOnClickListener(onClickListener)
        button6.setOnClickListener(onClickListener)
        button7.setOnClickListener(onClickListener)
        button8.setOnClickListener(onClickListener)
        button9.setOnClickListener(onClickListener)
        buttondelete.setOnClickListener {
            answerSb.setLength(0)
            textView9.text = getString(R.string.input_indicator)
        }
        buttonsubmit.setOnClickListener {
            if (answerSb.isEmpty()) {
                return@setOnClickListener
            }
            if (answerSb.toString().toIntOrNull() == mViewModel.getAnswer().value) {
                mViewModel.answerCorrect()
                answerSb.setLength(0)
                textView9.text = getString(R.string.answer_corrent)
            } else {
                val navigationController = Navigation.findNavController(it)
                if (mViewModel.winFlag) {
                    navigationController.navigate(R.id.action_qustionFragment_to_winFragment)
                    mViewModel.winFlag = false
                    mViewModel.save()
                } else {
                    navigationController.navigate(R.id.action_qustionFragment_to_loseFragment)
                }
                mViewModel.getCurrentScore().value = 0
            }
        }
    }

}
