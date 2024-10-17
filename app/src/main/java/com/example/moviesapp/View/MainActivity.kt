package com.example.moviesapp.View

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesapp.Adapters.FilmAdapter
import com.example.moviesapp.Adapters.SliderAdapter
import com.example.moviesapp.Models.SliderItems
import com.example.moviesapp.R
import com.example.moviesapp.ViewModel.MainViewModel
import com.example.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    private val sliderHandler = Handler(Looper.getMainLooper())
    private val sliderRunnable = Runnable {
        binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem+1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initFilms()
        initUpcoming()
        viewModel.loadMovies()


    }

    private fun initUpcoming() {
        viewModel.ucfilms.observe(this, Observer {
            binding.progressBarUpComing.visibility = View.VISIBLE
            binding.recyclerViewUpComing.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewUpComing.adapter = FilmAdapter(it)
            binding.progressBarUpComing.visibility = View.GONE
        })
        viewModel.loadUpcoming()
    }

    private fun initFilms() {
        viewModel.films.observe(this, Observer {
            binding.progressBarTop.visibility = View.VISIBLE
            binding.recyclerViewTop.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewTop.adapter = FilmAdapter(it)
            binding.progressBarTop.visibility = View.GONE
        })
        viewModel.loadFilms()
    }

    private fun initBanner() {
        viewModel.movies.observe(this, Observer { items ->
            binding.progressBarBanner.visibility = View.VISIBLE
            banner(items)
            binding.progressBarBanner.visibility = View.GONE

        })
    }

    private fun banner( items: MutableList<SliderItems>){
        val arraylist = ArrayList(items)
        binding.viewPager2.adapter = SliderAdapter(arraylist,binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3

        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r*0.15f
            }
        })

        binding.viewPager2.setPageTransformer(compositePageTransformer)
        binding.viewPager2.setCurrentItem(1)
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

}