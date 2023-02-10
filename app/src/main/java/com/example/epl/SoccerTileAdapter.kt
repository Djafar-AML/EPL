package com.example.epl

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SoccerTileAdapter(
    private val data: ArrayList<SoccerTile>,
    private val moreButtonCallback:(soccerTile: SoccerTile) -> Unit
) : RecyclerView.Adapter<SoccerTileAdapter.SoccerTileViewHolder>() {

    inner class SoccerTileViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_soccer_tile, parent, false)
    ) {

        private val headerImageView: ImageView = itemView.findViewById(R.id.teamHeaderImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val button: MaterialButton = itemView.findViewById(R.id.learnMoreButton)

        fun onBind(soccerTile: SoccerTile) {

            headerImageView.setImageResource(soccerTile.headerImageResourceId)
            titleTextView.text = soccerTile.title
            descriptionTextView.text = soccerTile.description
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

}