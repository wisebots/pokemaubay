package com.aubay.pokemon.home.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aubay.pokemon.core.extensions.inflate
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.model.remote.PokemonMove
import kotlinx.android.synthetic.main.item_list_poke_info.view.*

class MovesPokeAdapter constructor(private val itemClick: (PokemonMove) -> Unit) :
    ListAdapter<PokemonMove, MovesPokeAdapter.ViewHolder>(MovesPokeCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_poke_move)) {

        fun bind(item: PokemonMove) {
            itemView.tvName.text = item.move?.name
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }
}

private class MovesPokeCallback : DiffUtil.ItemCallback<PokemonMove>() {
    override fun areItemsTheSame(oldItem: PokemonMove, newItem: PokemonMove): Boolean =
        oldItem.move?.name == newItem.move?.name

    override fun areContentsTheSame(oldItem: PokemonMove, newItem: PokemonMove): Boolean =
        oldItem == newItem
}