package com.myapp.newyorktimes.ui.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.newyorktimes.R
import com.myapp.newyorktimes.databinding.ItemBookBinding
import com.myapp.newyorktimes.model.BookModel
import com.myapp.newyorktimes.util.OnItemClickCallBack
import com.myapp.newyorktimes.util.getString

class BookAdapter(private val items: ArrayList<BookModel>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private lateinit var binding: ItemBookBinding

    var onClickItem: OnItemClickCallBack? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(newItems: ArrayList<BookModel>) {
        val position = items.size
        items.addAll(newItems)
        notifyItemInserted(position)
    }

    inner class ViewHolder(private val _binding: ItemBookBinding) : RecyclerView.ViewHolder(_binding.root) {

        fun bind(item: BookModel) = with(_binding) {
            val noInfo = R.string.no_info.getString(title.context)

            title.text = item.name.takeIf { it.isNotBlank() } ?: R.string.no_name.getString(title.context)
            descriptionValue.text = item.description.takeIf { it.isNotBlank() } ?: noInfo
            authorValue.text = item.author.takeIf { it.isNotBlank() } ?: noInfo
            publisherValue.text = item.publisher.takeIf { it.isNotBlank() } ?: noInfo

            rankValue.text = item.rank.toString()

            Glide.with(bookImage.context)
                .load(item.image)
                .into(bookImage)

            itemView.setOnClickListener { onClickItem?.invoke(item.buyLink) }
        }
    }
}