package com.example.posterr.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posterr.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(com.example.navigation.R.id.root_container, MainContainerFragment())
                .commit()
        }
    }
}