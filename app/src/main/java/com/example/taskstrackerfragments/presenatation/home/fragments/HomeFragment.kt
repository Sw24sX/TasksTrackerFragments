package com.example.taskstrackerfragments.presenatation.home.fragments

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
import com.example.taskstrackerfragments.presenatation.home.viewmodel.HomeViewModel
import com.example.data.db.DataBase
import com.example.taskstrackerfragments.presenatation.home.viewpager.PageViewAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var activityContext: Context
    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val db = com.example.data.db.DataBase().getDB(activityContext)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(db) as T
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