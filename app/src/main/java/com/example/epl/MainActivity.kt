package com.example.epl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.epl.databinding.ActivityMainBinding
import com.example.epl.utils.soccerTileSerializableName

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var soccerTileList: ArrayList<SoccerTile>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        changeActionBarTitle()

        soccerTileList = soccerTileList()

        val soccerTileAdapter = SoccerTileAdapter(soccerTileList, ::soccerTileAdapterCallback)

        val soccerTileRecyclerView = binding.mainRecyclerview.apply {
            adapter = soccerTileAdapter
            setHasFixedSize(true)
        }

        soccerTileAdapter.notifyDataSetChanged()
    }

    private fun changeActionBarTitle() {
        supportActionBar?.title = "EPL Home"
    }

    private fun soccerTileList(): ArrayList<SoccerTile> {

        return ArrayList<SoccerTile>().apply {
            add(
                SoccerTile(
                    id = "manchester_united",
                    title = "Manchester United",
                    description = "Description of the club",
                    descriptionLong = "A longer description of the club!",
                    buttonText = "Learn more",
                    headerImageResourceId = R.drawable.manu_header,
                    headerImageResourceUrl = "https://i.pinimg.com/originals/8f/85/15/8f85159ed8306846b050386384893c1e.jpg"
                )
            )
            add(
                SoccerTile(
                    id = "manchester_city",
                    title = "Manchester City",
                    description = "Description of the club",
                    descriptionLong = "A longer description of the club!",
                    buttonText = "Learn more",
                    headerImageResourceId = R.drawable.mancity_header,
                    headerImageResourceUrl = "http://www.officialyayatoure.com/wp-content/uploads/2016/08/yaya-city-header.jpg"
                )
            )
            add(
                SoccerTile(
                    id = "tottenham",
                    title = "Tottenham",
                    description = "Description of the club",
                    descriptionLong = "A longer description of the club!",
                    buttonText = "Learn more",
                    headerImageResourceId = R.drawable.tottenham_header,
                    headerImageResourceUrl = "https://i.imgur.com/ajmkb.jpg"
                )
            )
            add(
                SoccerTile(
                    id = "chelsea",
                    title = "Chelsea",
                    description = "Description of the club",
                    descriptionLong = "A longer description of the club!",
                    buttonText = "Learn more",
                    headerImageResourceId = R.drawable.chelsea_header,
                    headerImageResourceUrl = "https://wallpaperstock.net/chelsea-logo%2c-high-wallpapers_55758_1680x1050.jpg"
                )
            )
            add(
                SoccerTile(
                    id = "leicester_city",
                    title = "Leicester City",
                    description = "Description of the club",
                    descriptionLong = "A longer description of the club!",
                    buttonText = "Learn more",
                    headerImageResourceId = R.drawable.leicester_header,
                    headerImageResourceUrl = "https://64.media.tumblr.com/87c9b804ffe8f4a0212be18368daf886/tumblr_od5g0cpoiE1ude0uno1_1280.jpg"
                )
            )
            add(
                SoccerTile(
                    id = "liverpool",
                    title = "Liverpool",
                    description = "Description of the club",
                    descriptionLong = "A longer description of the club!",
                    buttonText = "Learn more",
                    headerImageResourceId = R.drawable.liverpool_header,
                    headerImageResourceUrl = "https://theulsterfry.com/wp-content/uploads/2019/05/videoblocks-liverpool-fc-flag-waving-slow-motion-3d-rendering-blue-sky-background-editorial-animation-seamless-loop-4k_rjjucvdk_thumbnail-full01-1024x576.png"
                )
            )
        }
    }

    private fun soccerTileAdapterCallback(soccerTile: SoccerTile) {

        val intent = Intent(this, SoccerTileDetailActivity::class.java).apply {
            putExtra(soccerTileSerializableName, soccerTile)
        }

        startActivity(intent)

    }

}

//manU- https://i.pinimg.com/originals/8f/85/15/8f85159ed8306846b050386384893c1e.jpg

//manC- http://www.officialyayatoure.com/wp-content/uploads/2016/08/yaya-city-header.jpg

//tot- https://i.imgur.com/ajmkb.jpg

//chelsea- https://wallpaperstock.net/chelsea-logo%2c-high-wallpapers_55758_1680x1050.jpg

//leicester- https://64.media.tumblr.com/87c9b804ffe8f4a0212be18368daf886/tumblr_od5g0cpoiE1ude0uno1_1280.jpg

//liver- https://theulsterfry.com/wp-content/uploads/2019/05/videoblocks-liverpool-fc-flag-waving-slow-motion-3d-rendering-blue-sky-background-editorial-animation-seamless-loop-4k_rjjucvdk_thumbnail-full01-1024x576.png