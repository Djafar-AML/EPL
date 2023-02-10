package com.example.epl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val soccerTileList = soccerTileList()
    }

    private fun soccerTileList(): ArrayList<SocerTile> {

        return ArrayList<SocerTile>().apply {
            add(
                SocerTile(
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
                SocerTile(
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
                SocerTile(
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
                SocerTile(
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
                SocerTile(
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
                SocerTile(
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

}

//manU- https://i.pinimg.com/originals/8f/85/15/8f85159ed8306846b050386384893c1e.jpg

//manC- http://www.officialyayatoure.com/wp-content/uploads/2016/08/yaya-city-header.jpg

//tot- https://i.imgur.com/ajmkb.jpg

//chelsea- https://wallpaperstock.net/chelsea-logo%2c-high-wallpapers_55758_1680x1050.jpg

//leicester- https://64.media.tumblr.com/87c9b804ffe8f4a0212be18368daf886/tumblr_od5g0cpoiE1ude0uno1_1280.jpg

//liver- https://theulsterfry.com/wp-content/uploads/2019/05/videoblocks-liverpool-fc-flag-waving-slow-motion-3d-rendering-blue-sky-background-editorial-animation-seamless-loop-4k_rjjucvdk_thumbnail-full01-1024x576.png