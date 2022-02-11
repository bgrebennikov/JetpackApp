package com.edricaazaza.jetpackapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edricaazaza.jetpackapp.R
import com.edricaazaza.jetpackapp.databinding.ActivityMainBinding
import com.edricaazaza.jetpackapp.presentation.fragments.GameResultFragment
import com.edricaazaza.jetpackapp.presentation.fragments.HomeFragment
import com.edricaazaza.jetpackapp.presentation.fragments.WelcomeFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER_MAIN, WelcomeFragment())
                .commit()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FRAGMENT_CONTAINER_MAIN = R.id.FragmentContainerMain
    }

}