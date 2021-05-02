package com.example.taskstrackerfragments.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.datatask.AppDatabase
import com.example.taskstrackerfragments.ui.home.task.datatask.DataBase
import com.example.taskstrackerfragments.ui.home.viewpager.PageViewAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var activityContext: Context

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

        val db = DataBase().getDB(activityContext)

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