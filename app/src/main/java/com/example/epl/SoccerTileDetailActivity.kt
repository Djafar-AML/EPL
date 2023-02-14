package com.example.epl

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.epl.databinding.ActivitySoccerTileDetailBinding
import com.example.epl.utils.deserializeExtra
import com.example.epl.utils.soccerTileSerializableName

class SoccerTileDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySoccerTileDetailBinding.inflate(
            layoutInflater
        )
    }

    private val soccerTile by lazy {
        deserializeSoccerTile()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableBackButton()
        changeActionBarTitle()

        initViews(soccerTile)

    }

    private fun enableBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun changeActionBarTitle() {
        supportActionBar?.title = "Club Overview"
    }

    private fun deserializeSoccerTile(): SoccerTile {

        val soccerTile = intent.deserializeExtra(soccerTileSerializableName, SoccerTile::class.java)
        val st = MainActivity.soccerTileList.find {
            it.id == soccerTile?.id
        }

        return st ?: SoccerTile(
            title = "Whoops!",
            description = "Something went wrong, please try again.",
            headerImageResourceId = R.drawable.ic_broken_image_24
        )

    }

    private fun initViews(soccerTile: SoccerTile) {

        binding.apply {
            teamHeaderImageView.setImageResource(soccerTile.headerImageResourceId)
            titleTextView.text = soccerTile.title
            descriptionTextView.text = soccerTile.description
            descriptionLongTextView.text = soccerTile.descriptionLong
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            R.id.menuItemLink -> {
                navigateToTeamHomePage(soccerTile.teamUrl)
                return true
            }

            R.id.menuItemFavorite -> {

                flipIsFavorite()
                val favoriteIcon = favoriteIconType(soccerTile.isFavorite)
                item.setIcon(favoriteIcon)

                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_soccer_tile_detail, menu)

        val favoriteIcon = favoriteIconType(soccerTile.isFavorite)
        menu?.findItem(R.id.menuItemFavorite)?.setIcon(favoriteIcon)

        return true
    }

    private fun navigateToTeamHomePage(teamUrl: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(teamUrl))
        startActivity(intent)
    }

    private fun flipIsFavorite() {
        soccerTile.isFavorite = !soccerTile.isFavorite
    }

    private fun favoriteIconType(isFavorite: Boolean): Int {

        return if (isFavorite) {
            R.drawable.ic_favorite_24
        } else {
            R.drawable.ic_favorite_border_24
        }

    }

}