package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewPostField()
    }

    private fun setupNewPostField() {
        binding.homeTextInputEditText.addTextChangedListener {
            handleOnTextChanged(it.toString())
        }
    }

    private fun handleOnTextChanged(newText: String) = with(binding) {
        configNewPostState(newText.isNotEmpty())
        homePostCounter.text = (MAX_CHARACTERS - newText.length).toString()
    }

    private fun configNewPostState(enable: Boolean) = with(binding) {
        if (homeButton.isEnabled != enable) {
            homeButton.isEnabled = enable
            homePostCounter.isVisible = enable
        }
    }

    companion object {
        private const val MAX_CHARACTERS = 777
    }
}