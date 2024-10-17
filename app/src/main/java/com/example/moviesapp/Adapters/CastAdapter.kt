package com.example.moviesapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.Models.CastModel
import com.example.moviesapp.databinding.CastViewholderBinding

class CastAdapter(val casts: ArrayList<CastModel>):RecyclerView.Adapter<CastAdapter.Viewholder>() {
    private lateinit var context: Context


    inner class Viewholder(val binding:CastViewholderBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.Viewholder {
        context = parent.context
        val binding = CastViewholderBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CastAdapter.Viewholder, position: Int) {
        val cast = casts[position]
        holder.binding.actornameTxt.text = cast.Actor

        Glide.with(holder.itemView.context)
            .load(cast.PicUrl)
            .into(holder.binding.actorImg)
    }

    override fun getItemCount(): Int = casts.size
}