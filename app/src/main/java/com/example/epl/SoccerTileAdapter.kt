package com.example.epl

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.epl.utils.loadImageByPicasso
import com.google.android.material.button.MaterialButton

class SoccerTileAdapter(
    private val data: ArrayList<SoccerTile>,
    private val moreButtonCallback: (soccerTile: SoccerTile) -> Unit,
    private val onFavoriteClickCallback: (position: Int, soccerTile: SoccerTile) -> Unit,

) : RecyclerView.Adapter<SoccerTileAdapter.SoccerTileViewHolder>() {

    inner class SoccerTileViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_soccer_tile, parent, false)
    ) {

        private val headerImageView: AppCompatImageView = itemView.findViewById(R.id.teamHeaderImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val button: MaterialButton = itemView.findViewById(R.id.learnMoreButton)
        private val favoriteImageView: AppCompatImageView = itemView.findViewById(R.id.favoriteImageView)

        fun onBind(soccerTile: SoccerTile) {

//            headerImageView.setImageResource(soccerTile.headerImageResourceId)
            headerImageView.loadImageByPicasso(
                soccerTile.headerImageResourceUrl,
                soccerTile.headerImageResourceId
            )
            titleTextView.text = soccerTile.title
            descriptionTextView.text = soccerTile.description

            val iconType = favoriteIconType(soccerTile.isFavorite)
            favoriteImageView.setImageResource(iconType)

            favoriteImageView.setOnClickListener {
                onFavoriteClickCallback(adapterPosition, soccerTile)
            }

            button.setOnClickListener {
                moreButtonCallback(soccerTile)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerTileViewHolder {
        return SoccerTileViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SoccerTileViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount() = data.size

    private fun favoriteIconType(isFavorite: Boolean): Int {

        return if (isFavorite) {
            R.drawable.ic_favorite_24
        } else {
            R.drawable.ic_favorite_border_24
        }

    }

}