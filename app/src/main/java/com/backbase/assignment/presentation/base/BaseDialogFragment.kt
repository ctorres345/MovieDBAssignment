package com.backbase.assignment.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.backbase.assignment.util.FragmentComponents
import com.backbase.assignment.util.ViewModelFragmentComponents

abstract class BaseDialogFragment : DialogFragment(), FragmentComponents, ViewModelFragmentComponents {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initObservers()
        initAdapter()
        initUI()
    }
}