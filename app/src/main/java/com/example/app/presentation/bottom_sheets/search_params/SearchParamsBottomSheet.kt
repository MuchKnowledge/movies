package com.example.app.presentation.bottom_sheets.search_params

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.app.presentation.screens.main.MainViewModel
import com.example.app.presentation.view_binding.viewBinding
import com.example.movies.R
import com.example.movies.databinding.BottomSheetSearchParamsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchParamsBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_search_params) {

    private val mainViewModel: MainViewModel by viewModels()
    private val binding: BottomSheetSearchParamsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initInputLayouts()
    }

    private fun initListener() {
        with(binding) {
            buttonApply.setOnClickListener {
                mainViewModel.search(
                    title = inputEditTitle.editableText.toString(),
                    page = inputEditPage.editableText.toString().toInt()
                )
                dismiss()
            }
        }
    }

    private fun initInputLayouts() {
//        must be better way
        with(binding) {
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    buttonApply.isEnabled = s?.length!! >= 1
                }
            }
            inputEditTitle.addTextChangedListener(textWatcher)
            inputEditPage.addTextChangedListener(textWatcher)
        }
    }
}