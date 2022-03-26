package com.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidconnectionlesson2.databinding.ItemImageBinding
import com.example.retrofit.model.ImageModel
import com.example.retrofit.model.UrlModel
import com.squareup.picasso.Picasso

class ImagesAdapter(var list: ArrayList<ImageModel>) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    lateinit var imageAdapter: OnImageClickListener

    fun interface OnImageClickListener {
        fun onClick(urlModel: UrlModel)
    }

    fun setOnImageClickListener(imageListener: OnImageClickListener) {
        imageAdapter = imageListener
    }

    inner class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(urlModel: UrlModel) {
            Picasso.get().load(urlModel.small_s3).into(binding.image)
            binding.root.setOnClickListener {
                imageAdapter.onClick(urlModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position].urls)
    }

    override fun getItemCount(): Int = list.size
}