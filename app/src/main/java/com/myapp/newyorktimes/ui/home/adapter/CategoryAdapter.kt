package com.myapp.newyorktimes.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapp.newyorktimes.R
import com.myapp.newyorktimes.databinding.ItemCategoryBinding
import com.myapp.newyorktimes.model.CategoryModel
import com.myapp.newyorktimes.util.OnItemClickCallBack
import com.myapp.newyorktimes.util.getString

class CategoryAdapter(private val items: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var binding: ItemCategoryBinding

    var onClickItem: OnItemClickCallBack? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(newItems: ArrayList<CategoryModel>) {
        val position = items.size
        items.addAll(newItems)
        notifyItemInserted(position)
    }

    inner class ViewHolder(private val _binding: ItemCategoryBinding) : RecyclerView.ViewHolder(_binding.root) {

        fun bind(item: CategoryModel) = with(_binding) {
            title.text = item.name.takeIf { it.isNotBlank() } ?: R.string.no_name.getString(title.context)
            date.text = item.publishedDate
            itemView.setOnClickListener { onClickItem?.invoke(item.encodedName) }
        }
    }
}