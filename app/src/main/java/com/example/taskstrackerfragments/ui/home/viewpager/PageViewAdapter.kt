package com.example.taskstrackerfragments.ui.home.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.data.datatask.AppDatabase
import com.example.data.datatask.TaskType
import com.example.taskstrackerfragments.ui.home.taskfragments.TasksFragment
import com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel.DataBaseHost
import java.io.Serializable

class PageViewAdapter(fm: FragmentManager, private val db: com.example.data.datatask.AppDatabase) : FragmentPagerAdapter(fm), Serializable {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment{
        if(position == 0)
            return TasksFragment.newInstance(com.example.data.datatask.TaskType.GOOD, db)
        return TasksFragment.newInstance(com.example.data.datatask.TaskType.BAD, db)
    }

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Good"
        else -> "Bad"
    }
}