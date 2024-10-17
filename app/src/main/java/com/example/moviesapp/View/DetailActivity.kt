package com.example.moviesapp.View

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Adapters.CastAdapter
import com.example.moviesapp.Adapters.CategoryAdapter
import com.example.moviesapp.Models.FilmModel
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityDetailBinding
import eightbitlab.com.blurview.RenderScriptBlur

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

        binding.watchTrailerBTN.setOnClickListener {
            val id = item.Trailer.replace("https://www.youtube.com/watch?v=", "")
            val Appintent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
            val webintent = Intent(Intent.ACTION_VIEW, Uri.parse(item.Trailer))
            try{
                startActivity(Appintent)
            }catch (ex: ActivityNotFoundException){
                startActivity(webintent)
            }

        }
        binding.backBTN.setOnClickListener {
            finish()
        }

        val radius = 10F //bulanıklastirma seviyesi
        val decorView = window.decorView
        val root = decorView.findViewById<ViewGroup>(android.R.id.content)
        val windowsBackground = decorView.background

        binding.blurView.setupWith(root,RenderScriptBlur(this))
            .setFrameClearDrawable(windowsBackground)
            .setBlurRadius(radius)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true

        if(item.Genre != null){
            binding.genreRCV.adapter = CategoryAdapter(item.Genre)
            binding.genreRCV.layoutManager = LinearLayoutManager(this@DetailActivity,LinearLayoutManager.HORIZONTAL,false)
        }

        if(item.Casts !=null){
            binding.castRCV.adapter = CastAdapter(item.Casts)
            binding.castRCV.layoutManager = LinearLayoutManager(this@DetailActivity,LinearLayoutManager.HORIZONTAL,false)
        }

    }
}