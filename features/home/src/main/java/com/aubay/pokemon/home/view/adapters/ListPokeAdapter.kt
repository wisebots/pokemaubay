package com.aubay.pokemon.home.view.adapters

import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.aubay.pokemon.core.base.BasePagedListAdapter
import com.aubay.pokemon.core.extensions.loadImage
import com.aubay.pokemon.core.commons.IMAGE_POKE_URL
import com.aubay.pokemon.core.extensions.gone
import com.aubay.pokemon.core.extensions.visible
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.R.string.pokedex_subtitle
import com.aubay.pokemon.home.model.data.PokeInfo
import kotlinx.android.synthetic.main.item_list_poke_info.view.*

class ListPokeAdapter constructor(private val itemClick: (Int) -> Unit,
                                  private val favoriteClick: (PokeInfo, Boolean) -> Unit) :
    BasePagedListAdapter<PokeInfo>(
        itemsSame = { old, new -> old.id == new.id },
        contentsSame = { old, new -> old == new }
    ) {

    override val itemLayout = R.layout.item_list_poke_info

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val poke = getItem(position)
        val pokedexId = position+1

        poke?.let { pokemon ->
            holder.itemView.apply {
                if(pokedexId>0) ivImage.loadImage("${IMAGE_POKE_URL}${pokedexId}.png")
                tvName.text = pokemon.name.capitalize()
                tvDescription.text = "${resources.getString(pokedex_subtitle)} #${pokedexId}"
            }
            holder.itemView.let { view ->
                view.setOnClickListener { itemClick.invoke(pokedexId) }
            }
            holder.itemView.ivHeart.let { view ->
                view.setOnClickListener {
                    favoriteClick.invoke(pokemon, true)
                    when(view.visibility) {
                        VISIBLE -> {
                            holder.itemView.ivHeart.gone()
                            holder.itemView.ivHeartFilled.visible()
                        }
                    }
                }

            }
            holder.itemView.ivHeartFilled.let { view ->
                view.setOnClickListener {
                    favoriteClick.invoke(pokemon, false)
                    when(view.visibility) {
                        VISIBLE -> {
                            holder.itemView.ivHeartFilled.gone()
                            holder.itemView.ivHeart.visible()
                        }
                    }
                }
            }
        }
    }

}
