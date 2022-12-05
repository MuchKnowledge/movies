package com.example.app.presentation.bottom_sheets.search_params

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.app.presentation.view_binding.viewBinding
import com.example.app.utils.extension.roundCorners
import com.example.movies.R
import com.example.movies.databinding.BottomSheetSearchParamsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchParamsBottomSheet(private val onClick: (String, Int) -> Unit) :
    BottomSheetDialogFragment(R.layout.bottom_sheet_search_params) {

    private val binding: BottomSheetSearchParamsBinding by viewBinding()
    private val viewModel: SearchParamsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        initViews()
        initListener()
        initInputLayouts()
    }

    private fun observeState() {
        viewModel.state.observe(this) {
            when (it) {
                is SearchParamsState.QueryValidation -> changeButtonVisibility(it.isPageValid, it.isTextValid)
            }
        }
    }

    private fun changeButtonVisibility(isPageValid: Boolean, isTextValid: Boolean) {
        binding.buttonApply.isEnabled = isPageValid && isTextValid
    }

    private fun initViews() {
        with(binding) {
            buttonApply.roundCorners(radius = 16f)
        }
    }

    private fun initListener() {
        with(binding) {
            buttonApply.setOnClickListener {
                onClick(
                    inputEditTitle.editableText.toString(),
                    inputEditPage.editableText.toString().toInt()
                )
                dismiss()
            }
        }
    }

    private fun initInputLayouts() {
        with(binding) {
            inputEditTitle.doOnTextChanged { text, _, _, _ -> viewModel.validateText(text.toString()) }
            inputEditPage.doOnTextChanged { page, _, _, _ -> viewModel.validatePage(page.toString()) }
        }
    }
}