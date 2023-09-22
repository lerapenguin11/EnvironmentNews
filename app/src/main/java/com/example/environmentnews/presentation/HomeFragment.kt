package com.example.environmentnews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.environmentnews.MainActivity
import com.example.environmentnews.R
import com.example.environmentnews.business.database.AppDatabase
import com.example.environmentnews.business.model.Constants
import com.example.environmentnews.business.model.MoreNew
import com.example.environmentnews.business.repos.MoreNewRepository
import com.example.environmentnews.databinding.FragmentHomeBinding
import com.example.environmentnews.presentation.adapter.FeaturedTipsAdapter
import com.example.environmentnews.presentation.adapter.MoreNewAdapter
import com.example.environmentnews.presentation.adapter.listener.NewListener
import com.example.environmentnews.viewmodel.*

class HomeFragment : Fragment(), NewListener {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FeaturedViewModel
    private val adapter = FeaturedTipsAdapter()
    private lateinit var newViewModel : NewViewModel
    private lateinit var favoriteViewModel : FavoriteViewModel
    private val newAdapter = MoreNewAdapter(this)
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

        binding.constraintLayout2.setOnClickListener {
            openDetailsPopularTips(Constants.getPopularTips().id, Constants.getPopularTips().title,
                Constants.getPopularTips().icon, Constants.getPopularTips().description)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNavigationView()
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

    private fun openDetailsPopularTips(id: Int, title: Int, icon: Int, description: Int) {
        val bundle = Bundle()
        bundle.putInt("title", title)
        bundle.putInt("description", description)
        bundle.putInt("icon", icon)
        bundle.putInt("id", id)

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = DetailsNewFragment()
        fragment.arguments = bundle
        transaction?.replace(R.id.main_layout, fragment)
        transaction?.commit()
    }

    private fun observeDataFeaturedTips() {
        binding.rvFeaturedTips.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFeaturedTips.adapter = adapter

        viewModel.getResultFeaturedTips().observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })
    }

    override fun getNew(new: MoreNew) {
        val bundle = Bundle()
        bundle.putInt("title", new.title)
        bundle.putInt("description", new.description)
        bundle.putInt("icon", new.icon)
        bundle.putInt("id", new.id)

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = DetailsNewFragment()
        fragment.arguments = bundle
        transaction?.replace(R.id.main_layout, fragment)
        transaction?.commit()
    }
}