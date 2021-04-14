package com.example.taskstrackerfragments.ui.home.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task
import com.example.taskstrackerfragments.ui.home.taskfragments.TasksFragment
import java.io.Serializable

class PageViewAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm), Serializable {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment{
        return TasksFragment.newInstance()
//        return when(position) {
//            1 -> TasksFragment.newInstance(mutableListOf())
//            else -> TasksFragment.newInstance(mutableListOf())
//        }
    }

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Good"
        else -> "Bad"
    }
}