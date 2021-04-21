package com.example.taskstrackerfragments.ui.home.taskfragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskstrackerfragments.R
import com.example.taskstrackerfragments.model.Model


class BottomSheetFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        view.findViewById<Button>(R.id.find_name).setOnClickListener { viewModel.onClickFindTasksByName(view) }
        view.findViewById<Button>(R.id.sort_top).setOnClickListener { viewModel.onClickSortByTop() }
        view.findViewById<Button>(R.id.sort_bottom).setOnClickListener { viewModel.onClickSortByBottom() }
        return view
    }

    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = requireActivity().let {
            ViewModelProvider(it, object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return TasksViewModel(Model()) as T
                }
            }).get(TasksViewModel::class.java)
        }
    }
}