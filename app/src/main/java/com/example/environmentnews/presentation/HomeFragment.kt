package com.example.environmentnews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.environmentnews.business.database.AppDatabase
import com.example.environmentnews.business.model.Constants
import com.example.environmentnews.business.repos.MoreNewRepository
import com.example.environmentnews.databinding.FragmentHomeBinding
import com.example.environmentnews.presentation.adapter.FeaturedTipsAdapter
import com.example.environmentnews.presentation.adapter.MoreNewAdapter
import com.example.environmentnews.viewmodel.*

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FeaturedViewModel
    private val adapter = FeaturedTipsAdapter()
    private lateinit var newViewModel : NewViewModel
    private lateinit var favoriteViewModel : FavoriteViewModel
    private val newAdapter = MoreNewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(FeaturedViewModel::class.java)

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getDatabase(application).newDao()
        val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
        val repository = MoreNewRepository(dao, favoriteDao)
        val viewModelFactoryNews = NewViewModelFactory(repository)

        newViewModel = ViewModelProvider(this, viewModelFactoryNews).get(NewViewModel::class.java)

        val viewModelFactoryFav = FavoriteViewModelFactory(repository)
        favoriteViewModel = ViewModelProvider(this, viewModelFactoryFav).get(FavoriteViewModel::class.java)

        observeDataFeaturedTips()
        setDataPopularTips()
        observeDataNew()

        return binding.root
    }

    private fun observeDataNew() {
        binding.rvMoreNew.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMoreNew.adapter = newAdapter

        newViewModel.getResultNew().observe(viewLifecycleOwner, Observer {
            newAdapter.setItem(it)
        })
    }

    private fun setDataPopularTips() {
        binding.iconTipPopular.setImageResource(Constants.getPopularTips().icon)
        binding.tvTitlePopularTip.setText(Constants.getPopularTips().title)
    }

    private fun observeDataFeaturedTips() {
        binding.rvFeaturedTips.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFeaturedTips.adapter = adapter

        viewModel.getResultFeaturedTips().observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })
    }
}