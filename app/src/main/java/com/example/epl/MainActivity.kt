package com.example.epl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.epl.databinding.ActivityMainBinding
import com.example.epl.fragments.DetailFragment
import com.example.epl.fragments.ListFragment
import com.example.epl.prefs.Prefs
import com.example.epl.utils.simpleName
import com.example.epl.utils.soccerTileSerializableName

class MainActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val soccerTileList by lazy { soccerTileList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addFragment(ListFragment())
    }

    private fun addFragment(fragment: Fragment) {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(binding.fragmentContainerView.id, fragment)
        }

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
                    headerImageResourceUrl = "https://i.pinimg.com/originals/8f/85/15/8f85159ed8306846b050386384893c1e.jpg",
                    teamUrl = "https://www.manutd.com/",
                    isFavorite = Prefs.getSoccerTileIsFavorite("manchester_united")
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
                    headerImageResourceUrl = "http://www.officialyayatoure.com/wp-content/uploads/2016/08/yaya-city-header.jpg",
                    teamUrl = "https://www.mancity.com/",
                    isFavorite = Prefs.getSoccerTileIsFavorite("manchester_city")
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
                    headerImageResourceUrl = "https://i.imgur.com/ajmkb.jpg",
                    teamUrl = "https://www.tottenhamhotspur.com/",
                    isFavorite = Prefs.getSoccerTileIsFavorite("tottenham")
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
                    headerImageResourceUrl = "https://wallpaperstock.net/chelsea-logo%2c-high-wallpapers_55758_1680x1050.jpg",
                    teamUrl = "https://www.chelseafc.com/en",
                    isFavorite = Prefs.getSoccerTileIsFavorite("chelsea")
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
                    headerImageResourceUrl = "https://64.media.tumblr.com/87c9b804ffe8f4a0212be18368daf886/tumblr_od5g0cpoiE1ude0uno1_1280.jpg",
                    teamUrl = "https://www.lcfc.com/",
                    isFavorite = Prefs.getSoccerTileIsFavorite("leicester_city")
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
                    headerImageResourceUrl = "https://theulsterfry.com/wp-content/uploads/2019/05/videoblocks-liverpool-fc-flag-waving-slow-motion-3d-rendering-blue-sky-background-editorial-animation-seamless-loop-4k_rjjucvdk_thumbnail-full01-1024x576.png",
                    teamUrl = "https://www.liverpoolfc.com/",
                    isFavorite = Prefs.getSoccerTileIsFavorite("liverpool")
                )
            )
        }
    }

    fun soccerTileMoreButtonCallback(soccerTile: SoccerTile) {

        val bundle = bundleSoccerTile(soccerTile)

        val detailFragment = detailFragmentWithArgs(bundle)

        replaceFragment(detailFragment)

    }

    fun favoriteIconClickCallback(itemPosition: Int, soccerTile: SoccerTile) {

        val st = findSoccerTile(soccerTile)
        st?.let { flipIsFavorite(st) }

        updateListFragmentAdapter(itemPosition)

        st?.let { saveFavoriteStatusToPrefs(st.id, st.isFavorite) }

    }

    private fun bundleSoccerTile(soccerTile: SoccerTile) =
        Bundle().apply { putSerializable(soccerTileSerializableName, soccerTile) }

    private fun detailFragmentWithArgs(bundle: Bundle) =
        DetailFragment().apply { arguments = bundle }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(fragment.simpleName())
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            replace(binding.fragmentContainerView.id, fragment)
        }

    }

    private fun findSoccerTile(soccerTile: SoccerTile) =
        soccerTileList.find { it.id == soccerTile.id }

    private fun flipIsFavorite(soccerTile: SoccerTile) {
        soccerTile.isFavorite = !soccerTile.isFavorite
    }

    private fun updateListFragmentAdapter(itemPosition: Int) {
        val lf = supportFragmentManager.fragments[0] as? ListFragment
        lf?.onFavoriteClick(itemPosition)
    }

    private fun saveFavoriteStatusToPrefs(id: String, isFavorite: Boolean) {
        Prefs.setSoccerTileIsFavorite(id, isFavorite)
    }

}