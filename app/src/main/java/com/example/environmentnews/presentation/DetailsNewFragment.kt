package com.example.environmentnews.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.environmentnews.MainActivity
import com.example.environmentnews.R
import com.example.environmentnews.business.database.AppDatabase
import com.example.environmentnews.business.model.Favorite
import com.example.environmentnews.business.repos.MoreNewRepository
import com.example.environmentnews.databinding.FragmentDetailsNewBinding
import com.example.environmentnews.databinding.FragmentHomeBinding
import com.example.environmentnews.utilits.replaceFragmentMain
import com.example.environmentnews.viewmodel.FavoriteViewModel
import com.example.environmentnews.viewmodel.FavoriteViewModelFactory
import com.example.environmentnews.viewmodel.NewViewModel
import com.example.environmentnews.viewmodel.NewViewModelFactory
import kotlinx.coroutines.launch

class DetailsNewFragment : Fragment() {
    private var _binding : FragmentDetailsNewBinding? = null
    private val binding get() = _binding!!
    private lateinit var newViewModel : NewViewModel
    private lateinit var favoriteViewModel : FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsNewBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getDatabase(application).newDao()
        val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
        val repository = MoreNewRepository(dao, favoriteDao)
        val viewModelFactoryNews = NewViewModelFactory(repository)

        val window: Window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(requireActivity().resources.getColor(com.example.environmentnews.R.color.white))

        newViewModel = ViewModelProvider(this, viewModelFactoryNews).get(NewViewModel::class.java)

        val viewModelFactoryFav = FavoriteViewModelFactory(repository)
        favoriteViewModel = ViewModelProvider(this, viewModelFactoryFav).get(FavoriteViewModel::class.java)

        val displayTitle = arguments?.getInt("title")
        binding.tvTitleDetail.setText(displayTitle!!)

        val displayDescription = arguments?.getInt("description")
        binding.tvDescDetails.setText(displayDescription!!)

        val displayIcon = arguments?.getInt("icon")
        binding.icNewDetail.setImageResource(displayIcon!!)

        val newId = arguments?.getInt("id")

        newViewModel.isNewsFavorite(newId = newId!!).observe(viewLifecycleOwner, Observer {isFavorite ->
            if(isFavorite){
                binding.btSave.setImageResource(R.drawable.ic_save_click)
            }
            else {
                binding.btSave.setOnClickListener {
                    favoriteViewModel.viewModelScope.launch {
                        favoriteViewModel.insertFavorite(
                            favorite = Favorite(
                                newsId = newId, icon = displayIcon,
                                title = displayTitle, description = displayDescription
                            )
                        )
                    }
                }
            }
        })

        binding.imageView4.setOnClickListener { replaceFragmentMain(HomeFragment()) }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }
}