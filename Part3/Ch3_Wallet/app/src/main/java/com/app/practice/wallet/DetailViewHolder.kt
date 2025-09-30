package com.app.practice.wallet

import androidx.recyclerview.widget.RecyclerView
import com.app.practice.wallet.databinding.ItemDetailBinding
import com.app.practice.wallet.model.DetailItem

class DetailViewHolder(private val binding: ItemDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DetailItem) {
        binding.item = item
    }
}