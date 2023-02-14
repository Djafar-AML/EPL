package com.example.epl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.epl.MainActivity
import com.example.epl.SoccerTile
import com.example.epl.SoccerTileAdapter
import com.example.epl.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val activityHandler by lazy { activity as MainActivity }

    private val soccerTileList: ArrayList<SoccerTile>
        get() = activityHandler.soccerTileList

    private val soccerTileAdapter by lazy {
        SoccerTileAdapter(
            soccerTileList,
            activityHandler::soccerTileMoreButtonCallback,
            activityHandler::favoriteImageClickCallback
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

        changeActionBarTitle()
        initMainRecyclerview()

    }

    private fun changeActionBarTitle() {
        activityHandler.supportActionBar?.title = "EPL Home"
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

    fun onFavoriteClicked(itemPosition: Int) {
        soccerTileAdapter.notifyItemChanged(itemPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}