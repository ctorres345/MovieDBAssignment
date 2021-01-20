package com.backbase.assignment.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.backbase.assignment.databinding.DialogErrorBinding
import com.backbase.assignment.presentation.ext.makeGone
import com.backbase.assignment.presentation.ext.makeVisible
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ErrorDialog @Inject constructor(@ActivityContext private val context: Context) {
    private var dialog: AlertDialog? = null
    private lateinit var binding : DialogErrorBinding

    fun showErrorMessage(
        message: String,
        negativeActionName: String? = null,
        negativeAction: () -> Unit = { },
        positiveActionName: String? = null,
        positiveAction: () -> Unit = {}
    ) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        binding = DialogErrorBinding.inflate(inflater, null, false)
        binding.image.playAnimation()
        binding.errorMessage.text = message
        builder.setView(binding.root)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog?.let {
            setButtonActions(it, negativeActionName, negativeAction, positiveActionName, positiveAction)
        }
        dialog?.show()
    }

    private fun setButtonActions(
        dialog: AlertDialog,
        negativeActionName: String?,
        negativeAction: () -> Unit,
        positiveActionName: String?,
        positiveAction: () -> Unit
    ) {
        negativeActionName?.let {
            binding.negativeButton.text = it
        }
        binding.negativeButton.setOnClickListener {
            negativeAction.invoke()
            dialog.dismiss()
        }
        if(!positiveActionName.isNullOrEmpty()){
            binding.positiveButton.makeVisible()
            binding.positiveButton.apply {
                makeVisible()
                text = positiveActionName
                setOnClickListener {
                    positiveAction.invoke()
                    dialog.dismiss()
                }
            }
        }else{
            binding.positiveButton.makeGone()
        }
    }
}