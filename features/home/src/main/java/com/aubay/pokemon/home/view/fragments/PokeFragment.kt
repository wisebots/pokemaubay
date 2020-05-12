package com.aubay.pokemon.home.view.fragments

import androidx.fragment.app.Fragment
import com.aubay.pokemon.core.base.BaseFragment
import com.aubay.pokemon.core.commons.IMAGE_POKE_URL
import com.aubay.pokemon.core.extensions.invisible
import com.aubay.pokemon.core.extensions.loadImage
import com.aubay.pokemon.core.extensions.onClick
import com.aubay.pokemon.core.extensions.visible
import com.aubay.pokemon.core.navigation.OnSwipeTouchListener
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.model.data.PokeDescriptionLocal
import com.aubay.pokemon.home.model.remote.PokemonAbility
import com.aubay.pokemon.home.model.remote.PokemonMove
import com.aubay.pokemon.home.model.remote.PokemonType
import com.aubay.pokemon.home.view.adapters.AbilitiesPokeAdapter
import com.aubay.pokemon.home.view.adapters.MovesPokeAdapter
import com.aubay.pokemon.home.view.adapters.TypesPokeAdapter
import kotlinx.android.synthetic.main.fragment_detail_poke.*

class PokeDetailFragment : BaseFragment() {

    override fun getLayout() = R.layout.fragment_detail_poke

    private lateinit var fragment : Fragment

    val MAX_POKEDEX = 964

    private val mvItemClick: (PokemonMove) -> Unit = {}
    private val abItemClick: (PokemonAbility) -> Unit = {}
    private val tpItemClick: (PokemonType) -> Unit = {}

    private val movesAdapter = MovesPokeAdapter(mvItemClick)

    private val abilitiesAdapter = AbilitiesPokeAdapter(abItemClick)

    private val typesAdapter = TypesPokeAdapter(tpItemClick)

    override fun viewReady() {
        subscribeToData()

        subscribeActions()

        subscribeSwipe()

        rvPokeMoves.adapter = movesAdapter
        rvPokeAbilities.adapter = abilitiesAdapter
        rvPokeTypes.adapter = typesAdapter

        val pokeDescription = getParameters() as PokeDescriptionLocal?
        pokeDescription?.let {
            ivAppBar.loadImage("$IMAGE_POKE_URL${pokeDescription.id}.png")
            ctlToolbar.title = pokeDescription.name.capitalize()
            tvHeight.text = pokeDescription.height.toString()
            tvWeight.text = pokeDescription.weight.toString()
            tvExperience.text = pokeDescription.experience.toString()
            it.moves?.let { moves ->
                movesAdapter.submitList(moves)
            }
            it.abilities?.let { abilities->
                abilitiesAdapter.submitList(abilities)
            }
            it.types?.let { types->
                typesAdapter.submitList(types)
            }
        }
    }

    private fun subscribeToData() {
        fragment = this
    }

    private fun subscribeActions() {
        val pokeDescription = getParameters() as PokeDescriptionLocal?
        pokeDescription?.let {
            if(it.id > 1) { ivBefore.visible() }
            else{ ivBefore.invisible() }

            if(it.id >= MAX_POKEDEX) { ivNext.invisible() }
            else{ ivNext.visible() }
        }

        ivClose.onClick { onBackPressed() }
        ivNext.onClick { onNextPressed() }
        ivBefore.onClick { onBeforePressed() }
    }

    private fun subscribeSwipe() {
        rlPokeInfo.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                onNextPressed()
            }

            override fun onSwipeRight() {
                onBeforePressed()
            }
        })
    }

    companion object {
        fun newInstance() =
            PokeDetailFragment()
    }

}