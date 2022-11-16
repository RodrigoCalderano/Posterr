package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.databinding.FragmentHomeBinding
import com.example.ui.adapters.PostsListAdapter
import com.example.ui.models.OriginalPostUi
import com.example.ui.models.QuotePostUi
import com.example.ui.models.RepostUi
import com.example.ui.models.UiPost
import com.example.ui.showRepostBottomSheet
import kotlin.math.max

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val postsListAdapter: PostsListAdapter by lazy { PostsListAdapter() }

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
        setupPosts()

        // TODO RODRIGO remove this
        postsListAdapter.submitList(
            listOf<UiPost>(
                OriginalPostUi(
                    originalPostText = "Ut ac lacus mollis, viverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                    originalPostAuthor = "Rodrigo",
                    repostClickAction = {}
                ),
                RepostUi(
                    originalPostText = "Ut ac fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                    originalPostAuthor = "João",
                    repostClickAction = {
                        Toast.makeText(
                            this@HomeFragment.context,
                            it.originalPostAuthor,
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    userNameAuthor = "Matheus"
                ),
                QuotePostUi(
                    originalPostText = "iverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                    originalPostAuthor = "lar",
                    repostClickAction = { uiPostClicked ->
                        showRepostBottomSheet(uiPostClicked, ::onRepostClicked)
                    },
                    userNameAuthor = "RodrigoQuotador",
                    additionalQuoteText = "Achei muito interessante esse post!!!!"
                ),
                OriginalPostUi(
                    originalPostText = "aaaaaaaaaaaaUt ac lacus mollis, viverra dolor vitae, fermentum quam. Donec interdum quis sem sed porta. Etiam vel nisl et nulla ullamcorper interdum sit amet eget dui. Nulla eleifend sodales orci quis accumsan. Morbi bibendum luctus erat, vitae aliquet arcu feugiat sed. Vestibulum a risus non mauris blandit tempus vel sit amet risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer non mi urna. Phasellus maximus euismod eros, sit amet cursus turpis consectetur ut. Phasellus nibh diam, suscipit ut finibus tincidunt, bibendum vitae velit.",
                    originalPostAuthor = "Rodrigo",
                    repostClickAction = {}
                ),
            )
        )
    }

    private fun onRepostClicked(post: UiPost, quoteText: String) {
        Toast.makeText(
            this@HomeFragment.context,
            quoteText,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun setupPosts() = with(binding.rvPosts) {
        layoutManager = LinearLayoutManager(
            this@HomeFragment.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = postsListAdapter
    }

    private fun setupNewPostField() {
        binding.homeTextInputEditText.addTextChangedListener {
            handleOnTextChanged(it.toString())
        }
    }

    private fun handleOnTextChanged(newText: String) = with(binding) {
        configNewPostState(newText.isNotEmpty())
        homePostCounter.text = max(MAX_CHARACTERS - newText.length, ZERO_CHARACTERS).toString()
    }

    private fun configNewPostState(enable: Boolean) = with(binding) {
        if (homeButton.isVisible != enable) {
            homeButton.isVisible = enable
            homePostCounter.isVisible = enable
            homePostCounterText.isVisible = enable
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MAX_CHARACTERS = 777
        private const val ZERO_CHARACTERS = 0
    }
}