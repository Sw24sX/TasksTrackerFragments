package com.example.taskstrackerfragments.ui.home.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.taskstrackerfragments.ui.home.task.datatask.AppDatabase
import com.example.taskstrackerfragments.ui.home.task.datatask.TaskType
import com.example.taskstrackerfragments.ui.home.taskfragments.TasksFragment
import java.io.Serializable

class PageViewAdapter(fm: FragmentManager, private val db: AppDatabase) : FragmentPagerAdapter(fm), Serializable {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment{
        if(position == 0)
            return TasksFragment.newInstance(TaskType.GOOD, db)
        return TasksFragment.newInstance(TaskType.BAD, db)
    }

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Good"
        else -> "Bad"
    }
}