package com.example.taskstrackerfragments.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.OnTaskClickListener
import com.example.taskstrackerfragments.ui.home.task.RecyclerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task
import com.example.taskstrackerfragments.ui.home.taskfragments.GoodTasksFragment
import com.example.taskstrackerfragments.ui.home.viewpager.PageViewAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment(), OnTaskClickListener {

    //private lateinit var homeViewModel: HomeViewModel
    private lateinit var activityContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val pageViewAdapter = PageViewAdapter(activityContext, childFragmentManager)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = pageViewAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStateClick(task: Task, position: Int) {
        TODO("Not yet implemented")
    }
}