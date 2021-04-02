package com.example.taskstrackerfragments.ui.home.taskfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.task.OnTaskClickListener
import com.example.taskstrackerfragments.ui.home.task.RecyclerAdapter
import com.example.taskstrackerfragments.ui.home.task.Task

class GoodTasksFragment: Fragment(), OnTaskClickListener {
    companion object {
        fun newInstance() : GoodTasksFragment {
            return GoodTasksFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.good_tasks_fragment, container, false)
        val recyclerAdapter = RecyclerAdapter(mutableListOf(Task.default(), Task.default()), this)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerAdapter
        return view
    }

    override fun onStateClick(task: Task, position: Int) {
        TODO("Not yet implemented")
    }
}