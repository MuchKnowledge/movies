package com.example.app.presentation.bottom_sheets.search_params

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.app.presentation.screens.main.MainViewModel
import com.example.app.presentation.view_binding.viewBinding
import com.example.app.utils.extension.roundCorners
import com.example.movies.R
import com.example.movies.databinding.BottomSheetSearchParamsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchParamsBottomSheet(
    private val onClick: (String, Int) -> Unit
) : BottomSheetDialogFragment(R.layout.bottom_sheet_search_params) {

    private val binding: BottomSheetSearchParamsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()
        initInputLayouts()
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
//        must be better way
        var isTitleTextValid = false
        var isPageTextValid = false
        with(binding) {
            inputEditTitle.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    isTitleTextValid = s?.length!! >= 1
                    buttonApply.isEnabled = isPageTextValid && isTitleTextValid
                }
            })
            inputEditPage.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    isPageTextValid = s?.length!! >= 1
                    buttonApply.isEnabled = isPageTextValid && isTitleTextValid
                }
            })
        }
    }
}