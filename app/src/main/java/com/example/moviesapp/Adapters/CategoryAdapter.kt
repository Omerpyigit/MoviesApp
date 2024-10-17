package com.example.moviesapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.CategoryViewholderBinding

class CategoryAdapter(val items: List<String>):RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context: Context


    inner class  Viewholder(val binding:CategoryViewholderBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context = parent.context
        val binding = CategoryViewholderBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.categoryTxt.text = item
    }

    override fun getItemCount(): Int = items.size
}