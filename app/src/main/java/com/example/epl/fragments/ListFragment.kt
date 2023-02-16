package com.example.epl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.epl.SoccerTile
import com.example.epl.SoccerTileAdapter
import com.example.epl.base.BaseFragment
import com.example.epl.databinding.FragmentListBinding
import com.example.epl.prefs.Prefs

class ListFragment : BaseFragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val soccerTileList: ArrayList<SoccerTile>
        get() = activityHandler.soccerTileList

    private val soccerTileAdapter by lazy {
        SoccerTileAdapter(
            soccerTileList,
            activityHandler::soccerTileMoreButtonCallback,
            ::favoriteIconClickCallback
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionbarTitle("EPL Home")
        initMainRecyclerview()

    }


    private fun initMainRecyclerview() {
        binding.mainRecyclerview.apply {
            adapter = soccerTileAdapter
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()

        disableBackButton()
        soccerTileAdapter.notifyDataSetChanged()

    }

    private fun disableBackButton() {
        activityHandler.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun favoriteIconClickCallback(itemPosition: Int, soccerTile: SoccerTile) {

        val st = findSoccerTile(soccerTile)
        st?.let { flipIsFavorite(st) }

        updateListFragmentAdapter(itemPosition)

        st?.let { saveFavoriteStatusToPrefs(st.id, st.isFavorite) }

    }

    private fun findSoccerTile(soccerTile: SoccerTile) =
        soccerTileList.find { it.id == soccerTile.id }

    private fun flipIsFavorite(soccerTile: SoccerTile) {
        soccerTile.isFavorite = !soccerTile.isFavorite
    }

    private fun updateListFragmentAdapter(itemPosition: Int) {
        soccerTileAdapter.notifyItemChanged(itemPosition)
    }

    private fun saveFavoriteStatusToPrefs(id: String, isFavorite: Boolean) {
        Prefs.setSoccerTileIsFavorite(id, isFavorite)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}