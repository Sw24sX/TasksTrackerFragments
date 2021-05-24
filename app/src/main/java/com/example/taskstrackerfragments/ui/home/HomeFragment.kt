package com.example.taskstrackerfragments.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.homeviewmodel.HomeViewModel
import com.example.taskstrackerfragments.App
import com.example.taskstrackerfragments.dagger.HabitComponent
import com.example.taskstrackerfragments.ui.home.viewpager.PageViewAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var activityContext: Context
    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context

    }
    lateinit var applicationComponent: HabitComponent
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        applicationComponent = (requireActivity().applicationContext as App).applicationComponent
        val db = com.example.data.datatask.DataBase().getDB(activityContext)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(applicationComponent) as T
            }
        }).get(HomeViewModel::class.java)

        val pageViewAdapter = PageViewAdapter(childFragmentManager, db)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = pageViewAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        return view
    }

    companion object {
        const val TAG: String = "HomeFragment"
    }
}