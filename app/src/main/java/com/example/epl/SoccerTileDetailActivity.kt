package com.example.epl

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.epl.databinding.ActivitySoccerTileDetailBinding
import com.example.epl.utils.deserializeExtra
import com.example.epl.utils.soccerTileSerializableName

class SoccerTileDetailActivity : AppCompatActivity() {

    private val binding: ActivitySoccerTileDetailBinding by lazy {
        ActivitySoccerTileDetailBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableBackButton()
        changeActionBarTitle()

        val soccerTile = deserializeSoccerTile()
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
        return soccerTile ?: SoccerTile(
            title = "Whoops!",
            description = "Something went wrong, please try again."
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

            else -> {
                super.onOptionsItemSelected(item)
            }

        }

    }

}