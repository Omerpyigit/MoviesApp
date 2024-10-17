package com.example.moviesapp.View

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Models.FilmModel
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailBinding
    lateinit var item: FilmModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bundle()
    }

    private fun bundle() {
        item = intent.getParcelableExtra("object")!!
        binding.titleTXT.text = item.Title
        binding.imdbTxt.text = "IMDB " + item.Imdb
        binding.movieTimes.text = item.Year.toString() + " " + item.Time
        binding.movieSummery.text = item.Description

        val reqoptions = RequestOptions()
            .transform(CenterCrop(), GranularRoundedCorners(0F,0F, 50F,50F))

        Glide.with(this)
            .load(item.Poster)
            .apply(reqoptions)
            .into(binding.filmPic)
    }
}