package com.example.ui.extensions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.models.domain.Post
import com.example.ui.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

fun Fragment.showRepostBottomSheet(post: Post, callback: (Post, String) -> Unit) {
    BottomSheetDialog(requireContext(), R.style.DialogStyle).apply {
        setContentView(layoutInflater.inflate(R.layout.repost_bottom_sheet, null))
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        val repostBottomSheetTextInput =
            findViewById<TextInputEditText>(R.id.repostBottomSheetTextInput)
        val repostBottomSheetButton =
            findViewById<Button>(R.id.repostBottomSheetButton)

        repostBottomSheetTextInput?.addTextChangedListener {
            repostBottomSheetButton?.text =
                if (it.toString().isEmpty())
                    getString(R.string.repost) else getString(R.string.quote)
        }

        repostBottomSheetButton?.setOnClickListener {
            callback(post, repostBottomSheetTextInput?.text.toString())
            dismiss()
        }
        show()
    }
}

fun Fragment.closeKeyBoard() =
    (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
        hideSoftInputFromWindow(requireView().windowToken, 0)
    }