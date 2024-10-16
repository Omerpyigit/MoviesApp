package com.example.moviesapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Models.FilmModel
import com.example.moviesapp.View.DetailActivity
import com.example.moviesapp.databinding.FilmViewholderBinding

class FilmAdapter(val items: MutableList<FilmModel>):RecyclerView.Adapter<FilmAdapter.Viewholder>()  {
    private lateinit var context: Context


    inner class Viewholder (val binding: FilmViewholderBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.Viewholder {
        context = parent.context
        val binding = FilmViewholderBinding.inflate(LayoutInflater.from(context),parent,false )
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: FilmAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.nameTxt.text = item.Title

        val reqoptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(30))


        Glide.with(holder.itemView.context)
            .load(item.Poster)
            .apply(reqoptions)
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}