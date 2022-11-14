package com.example.posterr.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.home.HomeFragment
import com.example.posterr.R
import com.example.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainContainerFragment : Fragment(R.layout.fragment_main_container) {

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val profileFragment: ProfileFragment by lazy { ProfileFragment() }
    private var activeFragment: Fragment = homeFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            addFragments()
            setUpBottomNavItemSelectedListener()
        }
    }

    private fun addFragments() = with(childFragmentManager) {
        if (fragments.isEmpty()) {
            beginTransaction()
                .add(R.id.mainContainerView, profileFragment, PROFILE_FRAGMENT)
                .hide(profileFragment)
                .add(R.id.mainContainerView, homeFragment, HOME_FRAGMENT)
                .commit()
        }
    }

    private fun setUpBottomNavItemSelectedListener() {
        view?.findViewById<BottomNavigationView>(R.id.bottomNavHome)
            ?.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_feature_home -> {
                        childFragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .show(homeFragment)
                            .commit()
                        this.activeFragment = homeFragment
                        return@setOnItemSelectedListener true
                    }
                    R.id.nav_feature_profile -> {
                        childFragmentManager.beginTransaction()
                            .hide(activeFragment)
                            .show(profileFragment)
                            .commit()
                        this.activeFragment = profileFragment
                        return@setOnItemSelectedListener true
                    }
                }
                return@setOnItemSelectedListener false
            }
    }

    private companion object {
        private const val PROFILE_FRAGMENT = "PROFILE_FRAGMENT"
        private const val HOME_FRAGMENT = "HOME_FRAGMENT"
    }
}