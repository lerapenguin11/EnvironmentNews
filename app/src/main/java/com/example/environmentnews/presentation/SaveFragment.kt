package com.example.environmentnews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.environmentnews.R
import com.example.environmentnews.business.database.AppDatabase
import com.example.environmentnews.business.model.Favorite
import com.example.environmentnews.business.repos.MoreNewRepository
import com.example.environmentnews.databinding.FragmentHomeBinding
import com.example.environmentnews.databinding.FragmentSaveBinding
import com.example.environmentnews.presentation.adapter.FavoriteAdapter
import com.example.environmentnews.presentation.adapter.listener.FavListener
import com.example.environmentnews.viewmodel.FavoriteViewModel
import com.example.environmentnews.viewmodel.FavoriteViewModelFactory
import com.example.environmentnews.viewmodel.NewViewModel
import com.example.environmentnews.viewmodel.NewViewModelFactory
import kotlinx.coroutines.launch

class SaveFragment : Fragment(), FavListener {
    private var _binding : FragmentSaveBinding? = null
    private val binding get() = _binding!!
    private val adapter = FavoriteAdapter(this, this)
    private lateinit var newViewModel : NewViewModel
    private lateinit var favoriteViewModel : FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSaveBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getDatabase(application).newDao()
        val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
        val repository = MoreNewRepository(dao, favoriteDao)
        val viewModelFactoryNews = NewViewModelFactory(repository)

        newViewModel = ViewModelProvider(this, viewModelFactoryNews).get(NewViewModel::class.java)

        val viewModelFactoryFav = FavoriteViewModelFactory(repository)
        favoriteViewModel = ViewModelProvider(this, viewModelFactoryFav).get(FavoriteViewModel::class.java)

        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        binding.rvSave.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvSave.adapter = adapter

        favoriteViewModel.allFavorites.observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })
    }

    override fun getFavorite(fav: Favorite) {
        //открыть детали
    }

    override fun getFavoriteDelete(fav: Favorite) {
        favoriteViewModel.allFavorites.observe(viewLifecycleOwner, Observer {
            favoriteViewModel.viewModelScope.launch {
                favoriteViewModel.deleteFavorite(
                    favorite = Favorite(newsId = fav.newsId, icon = fav.icon,
                        title = fav.title, description = fav.description, id = fav.id )
                )
            }
        })
    }
}