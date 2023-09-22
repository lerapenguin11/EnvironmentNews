package com.example.environmentnews.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.environmentnews.MainActivity
import com.example.environmentnews.R
import com.example.environmentnews.business.database.AppDatabase
import com.example.environmentnews.business.model.Constants
import com.example.environmentnews.business.model.FeaturedTipsModel
import com.example.environmentnews.business.model.MoreNew
import com.example.environmentnews.business.repos.MoreNewRepository
import com.example.environmentnews.databinding.FragmentHomeBinding
import com.example.environmentnews.presentation.adapter.FeaturedTipsAdapter
import com.example.environmentnews.presentation.adapter.MoreNewAdapter
import com.example.environmentnews.presentation.adapter.listener.FeaturedListener
import com.example.environmentnews.presentation.adapter.listener.NewListener
import com.example.environmentnews.viewmodel.*

class HomeFragment : Fragment(), NewListener, FeaturedListener {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FeaturedViewModel
    private val adapter = FeaturedTipsAdapter(this)
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

        val window: Window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(requireActivity().resources.getColor(com.example.environmentnews.R.color.background))

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
        binding.rvFeaturedTips.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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

    override fun getFeaturedTips(featured: FeaturedTipsModel) {
        val bundle = Bundle()
        bundle.putInt("title", featured.title)
        bundle.putInt("description", featured.description)
        bundle.putInt("icon", featured.icon)
        bundle.putInt("temp", featured.temp)
        bundle.putInt("weather", featured.weather)
        bundle.putInt("water", featured.water)

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = FeaturedTipsDetailsFragment()
        fragment.arguments = bundle
        transaction?.replace(R.id.main_layout, fragment)
        transaction?.commit()
    }
}