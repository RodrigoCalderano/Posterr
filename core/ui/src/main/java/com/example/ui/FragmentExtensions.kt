package com.example.ui

import android.view.WindowManager
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.ui.models.UiPost
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

fun Fragment.showRepostBottomSheet(uiPost: UiPost, callback: (UiPost, String) -> Unit) {
    BottomSheetDialog(requireContext(), R.style.DialogStyle).apply {
        setContentView(
            layoutInflater.inflate(
                R.layout.repost_bottom_sheet,
                null
            )
        )
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        val repostBottomSheetTextInput =
            findViewById<TextInputEditText>(com.example.ui.R.id.repostBottomSheetTextInput)
        val repostBottomSheetButton =
            findViewById<Button>(com.example.ui.R.id.repostBottomSheetButton)

        repostBottomSheetTextInput?.addTextChangedListener {
            repostBottomSheetButton?.text =
                if (it.toString().isEmpty())
                    getString(R.string.repost) else getString(R.string.quote)
        }

        repostBottomSheetButton?.setOnClickListener {
            callback(uiPost, repostBottomSheetTextInput?.text.toString())
            dismiss()
        }
        show()
    }
}