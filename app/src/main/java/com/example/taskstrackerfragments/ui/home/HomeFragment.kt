package com.example.taskstrackerfragments.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.viewpager.PageViewAdapter
import com.example.taskstrackerfragments.ui.home.viewpager.GoodBadTasks
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var activityContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    private lateinit var goodBadTasks: GoodBadTasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true;
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        goodBadTasks = GoodBadTasks(mutableListOf(), mutableListOf())
        if (savedInstanceState != null)
            goodBadTasks = savedInstanceState.getSerializable(GOOD_BAD_TASKS) as GoodBadTasks

        val pageViewAdapter = PageViewAdapter(goodBadTasks, childFragmentManager)

        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = pageViewAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(GOOD_BAD_TASKS, goodBadTasks)
    }

    companion object {
        const val GOOD_BAD_TASKS: String = "tasks"
        const val TAG: String = "HomeFragment"
    }
}