package com.aubay.pokemon.home.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.aubay.pokemon.core.base.*
import com.aubay.pokemon.core.extensions.*
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.di.HomeModule
import com.aubay.pokemon.home.model.data.PokeDescriptionLocal
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.view.adapters.ListPokeAdapter
import com.aubay.pokemon.home.view.fragments.AddProfileFragment
import com.aubay.pokemon.home.view.fragments.PokeDetailFragment
import com.aubay.pokemon.home.viewmodel.FavoriteViewModel
import com.aubay.pokemon.home.viewmodel.PokemonDescriptionViewModel
import com.aubay.pokemon.home.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_list_poke_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_home

    private val viewModel: PokemonViewModel by viewModel()

    private val detailViewModel: PokemonDescriptionViewModel by viewModel()

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var pokeSelected : PokeDescriptionLocal

    private var pokedexOpened : Int = 1

    private val itemClick: (Int) -> Unit = {
        pokedexOpened = it
        detailViewModel.showDescription(it)
        fabHomeMail.hide()
    }

    private val favoriteClick: (PokeInfo, Boolean) -> Unit = { pokeInfo: PokeInfo, favorited: Boolean ->
        favoriteViewModel.favorite(pokeInfo, favorited)
        when(favorited){
            true -> snackbar(getString(R.string.favorited_message), rlPokeList)
            false -> snackbar(getString(R.string.unfavorited_message), rlPokeList)
        }
    }

    private val adapter = ListPokeAdapter(itemClick, favoriteClick)

    private fun injectModules() = loadModules

    private val loadModules by lazy {
        HomeModule.init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectModules()

        subscribeFloating()

        showLoading(pbwLoadingProgress)
        subscribePokeListToData()

        subscribeDescriptionToData()

        setSupportActionBar(toolbar);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAddLinkedin -> {
                webviewContainer.visible()
                addFragment(AddProfileFragment.newInstance(), webviewContainer.id, true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun navigate() {
    }

    override fun getParameters(): Any {
        return pokeSelected
    }

    override fun onBackPressed() {
        if(webviewContainer.visibility == View.VISIBLE) {
            webviewContainer.invisible()
            app_bar.visible()
            fabHomeMail.show()
            goBack()
        }
        else{
            parent.onBackPressed()
        }
    }

    override fun onNextPressed() {
        goBack()
        detailViewModel.showDescription(++pokedexOpened)
    }

    override fun onBeforePressed() {
        goBack()
        detailViewModel.showDescription(--pokedexOpened)
    }

    override fun subscribeBottomNavigation() {
        TODO("Not yet implemented")
    }

    private fun subscribePokeListToData() {
        rvPokeList.adapter = adapter

        viewModel.pokes.observe(this, Observer { adapter.submitList(it) })

        viewModel.getPokesState.observe(this, Observer {
            val animate = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
            pbwLoadingProgress?.startAnimation(animate)
            hideLoading(pbwLoadingProgress)
        })

    }

    private fun subscribeDescriptionToData() {
        detailViewModel.viewState.subscribe(this, ::handleDescriptionViewState)
        detailViewModel.pokeDescription.observe(this, Observer { showDescription(it) })
    }

    private fun showDescription(pokeDescription: PokeDescriptionLocal?) {
        pokeDescription?.let { pokeSelected = it }

        addFragment(PokeDetailFragment.newInstance(), webviewContainer.id, ivImage, true)
        webviewContainer.visible()
        app_bar.invisible()

        val animate = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        pbwLoadingProgress?.startAnimation(animate)
        hideLoading(pbwLoadingProgress)
    }

    private fun handleDescriptionViewState(viewState: ViewState<PokeDescriptionLocal>) {
        when (viewState) {
            is Loading -> showLoading(pbwLoadingProgress)
            is Success -> showDescription(viewState.data)
            is Error -> handleError(viewState.error.localizedMessage)
            is NoInternetState -> showNoInternetError()
            is Nothing -> handleError("General Failure")
        }
    }

    private fun handleError(error: String?) {
        hideLoading(pbwLoadingProgress)
        showError(error, rlPokeList)
    }

    private fun subscribeFloating() {
        fabHomeMail.onClick {
            startActivity(
                Intent.createChooser(
                    sendEmail(
                        getString(R.string.home_email_default),
                        getString(R.string.home_subject_default),
                        getString(R.string.home_description_default)
                    ),
                    getString(R.string.home_email_chooser_title)
                )
            )
        }
    }

    private fun showNoInternetError() {
        hideLoading(pbwLoadingProgress)
        snackbar(getString(com.aubay.pokemon.core.R.string.no_internet_error_message), rlPokeList)
    }
}