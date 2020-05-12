package com.aubay.pokemon.home.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aubay.pokemon.core.extensions.inflate
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.model.remote.PokemonType
import kotlinx.android.synthetic.main.item_list_poke_type.view.*

class TypesPokeAdapter constructor(private val itemClick: (PokemonType) -> Unit) :
    ListAdapter<PokemonType, TypesPokeAdapter.ViewHolder>(TypesPokeCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_poke_type)) {

        fun bind(item: PokemonType) {
            itemView.tvName.text = item.type?.name
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }
}

private class TypesPokeCallback : DiffUtil.ItemCallback<PokemonType>() {
    override fun areItemsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean =
        oldItem.type?.name == newItem.type?.name

    override fun areContentsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean =
        oldItem == newItem
}