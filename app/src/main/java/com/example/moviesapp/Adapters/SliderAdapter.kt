package com.example.moviesapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Models.SliderItems
import com.example.moviesapp.databinding.SliderViewholderBinding

class SliderAdapter(val items: List<SliderItems>):RecyclerView.Adapter<SliderAdapter.Viewholder>() {
    private lateinit var context: Context

    inner class Viewholder(binding: SliderViewholderBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapter.Viewholder {
        context = parent.context
        val binding = SliderViewholderBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.Viewholder, position: Int) {

    }

    override fun getItemCount(): Int = items.size
}