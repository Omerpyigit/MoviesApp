package com.example.moviesapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Models.SliderItems
import com.example.moviesapp.databinding.SliderViewholderBinding

class SliderAdapter(val items: MutableList<SliderItems>, val viewPager2: ViewPager2):RecyclerView.Adapter<SliderAdapter.Viewholder>() {
    private lateinit var context: Context
    private val runnable = Runnable {
        items.addAll(items)
        notifyDataSetChanged()
    }

    inner class Viewholder(val binding: SliderViewholderBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapter.Viewholder {
        context = parent.context
        val binding = SliderViewholderBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.nameTxt.text = item.name
        holder.binding.yearTxt.text = item.year
        holder.binding.genreTxt.text = item.genre
        holder.binding.ageTxt.text = item.age
        holder.binding.hourTxt.text = item.time

        val reqoptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(60))

        Glide.with(holder.itemView.context)
            .load(item.image)
            .apply(reqoptions)
            .into(holder.binding.imageSlide)
        if(position == items.size - 2){
            viewPager2.post(runnable)
        }

    }

    override fun getItemCount(): Int = items.size
}