package com.aubay.pokemon.home.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aubay.pokemon.core.extensions.inflate
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.model.remote.PokemonAbility
import kotlinx.android.synthetic.main.item_list_poke_ability.view.*

class AbilitiesPokeAdapter constructor(private val itemClick: (PokemonAbility) -> Unit) :
    ListAdapter<PokemonAbility, AbilitiesPokeAdapter.ViewHolder>(AbilitiesPokeCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_poke_ability)) {

        fun bind(item: PokemonAbility) {
            itemView.tvName.text = item.ability?.name
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }
}

private class AbilitiesPokeCallback : DiffUtil.ItemCallback<PokemonAbility>() {
    override fun areItemsTheSame(oldItem: PokemonAbility, newItem: PokemonAbility): Boolean =
        oldItem.ability?.name == newItem.ability?.name

    override fun areContentsTheSame(oldItem: PokemonAbility, newItem: PokemonAbility): Boolean =
        oldItem == newItem
}