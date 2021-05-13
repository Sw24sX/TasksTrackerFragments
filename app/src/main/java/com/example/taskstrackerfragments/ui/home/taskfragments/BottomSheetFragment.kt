package com.example.taskstrackerfragments.ui.home.taskfragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.ui.home.taskfragments.viewmodel.TasksViewModel


class BottomSheetFragment: Fragment() {
    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = requireParentFragment().let {
            ViewModelProvider(it).get(TasksViewModel::class.java)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        view.findViewById<EditText>(R.id.task_name_find).addTextChangedListener { viewModel.filterTasksByName(view) }
        view.findViewById<Button>(R.id.sort_top).setOnClickListener { viewModel.sortTasksByTop() }
        view.findViewById<Button>(R.id.sort_bottom).setOnClickListener { viewModel.sortTasksByBottom() }
        return view
    }
}