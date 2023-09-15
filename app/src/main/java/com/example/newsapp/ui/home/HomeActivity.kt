package com.example.newsapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.ui.news.NewsFragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
lateinit var viewBinding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsFragment())
            .commit()
    }

}