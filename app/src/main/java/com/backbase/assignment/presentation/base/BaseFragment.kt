package com.backbase.assignment.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.backbase.assignment.util.FragmentComponents
import com.backbase.assignment.util.ViewModelFragmentComponents

abstract class BaseFragment : Fragment(), FragmentComponents, ViewModelFragmentComponents {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initObservers()
        initAdapter()
        initUI()
    }
}