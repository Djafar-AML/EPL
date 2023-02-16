package com.example.epl.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.epl.R
import com.example.epl.SoccerTile
import com.example.epl.base.BaseFragment
import com.example.epl.databinding.FragmentDetailBinding
import com.example.epl.prefs.Prefs
import com.example.epl.utils.deserialize
import com.example.epl.utils.loadImageByPicasso
import com.example.epl.utils.soccerTileSerializableName

class DetailFragment : BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val argSoccerTile by lazy {
        arguments?.deserialize(soccerTileSerializableName, SoccerTile::class.java)
    }

    private val soccerTile by lazy { soccerTile() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionbarTitle("Club Overview")
        enableBackButton()
        initMenuOptions()
        initViews(soccerTile)
    }

    private fun enableBackButton() {
        activityHandler.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMenuOptions() {

        activityHandler.addMenuProvider(
            menuProviderObject(),
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    private fun menuProviderObject() = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

            menuInflater.inflate(R.menu.menu_soccer_tile_detail, menu)
            val favoriteIcon = favoriteIconType(soccerTile.isFavorite)
            menu.findItem(R.id.menuItemFavorite)?.setIcon(favoriteIcon)

        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

            return when (menuItem.itemId) {

                android.R.id.home -> {

                    popBackStack()

                    true
                }

                R.id.menuItemLink -> {

                    browseTheTeamHomePage(soccerTile.teamUrl)

                    true
                }

                R.id.menuItemFavorite -> {

                    flipIsFavorite()
                    val favoriteIcon = favoriteIconType(soccerTile.isFavorite)
                    menuItem.setIcon(favoriteIcon)

                    saveFavoriteStatusToPrefs(soccerTile.id, soccerTile.isFavorite)

                    true
                }

                else -> {
                    false
                }

            }

        }
    }

    private fun popBackStack() {
        activityHandler.supportFragmentManager.popBackStack()
    }

    private fun browseTheTeamHomePage(teamUrl: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(teamUrl))
        startActivity(intent)
    }

    private fun flipIsFavorite() {
        soccerTile.isFavorite = !soccerTile.isFavorite
    }

    private fun initViews(soccerTile: SoccerTile) {

        binding.apply {
            teamHeaderImageView.loadImageByPicasso(
                soccerTile.headerImageResourceUrl,
                soccerTile.headerImageResourceId
            )
            titleTextView.text = soccerTile.title
            descriptionTextView.text = soccerTile.description
            descriptionLongTextView.text = soccerTile.descriptionLong
        }

    }

    private fun soccerTile(): SoccerTile {

        val _soccerTile = activityHandler.soccerTileList.find { it.id == argSoccerTile?.id }

        return _soccerTile ?: SoccerTile(
            title = "Whoops!",
            description = "Something went wrong, please try again.",
            headerImageResourceId = R.drawable.ic_broken_image_24
        )

    }

    private fun favoriteIconType(isFavorite: Boolean): Int {

        return if (isFavorite) {
            R.drawable.ic_favorite_24
        } else {
            R.drawable.ic_favorite_border_24
        }

    }

    private fun saveFavoriteStatusToPrefs(id: String, isFavorite: Boolean) {
        Prefs.setSoccerTileIsFavorite(id, isFavorite)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}