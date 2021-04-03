package com.example.taskstrackerfragments.ui.home.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.taskstrackerfragments.ui.home.taskfragments.GoodTasksFragment

class PageViewAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment{
        return GoodTasksFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Good"
        else -> "Bad"
    }
}