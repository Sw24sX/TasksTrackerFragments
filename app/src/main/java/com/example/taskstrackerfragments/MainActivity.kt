package com.example.taskstrackerfragments

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.taskstrackerfragments.ui.home.task.OnPutTaskInRecycler
import com.example.taskstrackerfragments.entities.Task
import com.example.taskstrackerfragments.entities.TaskType
import com.example.taskstrackerfragments.ui.home.taskfragments.ChangeTaskFragment

class MainActivity : AppCompatActivity(), OnChangeTask, OnCreateNewTask, OnSaveTask{

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_about), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private var returnTask: OnPutTaskInRecycler? = null
    private var position: Int? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (returnTask != null)
            outState.putSerializable(RETURN_TASK, returnTask)
        if(position != null)
            outState.putInt(POSITION, position!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(POSITION)) position = savedInstanceState.getInt(POSITION)
        if(savedInstanceState.containsKey(RETURN_TASK))
            returnTask = savedInstanceState.getSerializable(RETURN_TASK) as OnPutTaskInRecycler
    }

    override fun createTask(fragment: OnPutTaskInRecycler, typeTask: TaskType) {
        returnTask = fragment
        supportFragmentManager.beginTransaction()
                .hide(supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!)
                .add(R.id.app_bar_main, ChangeTaskFragment.newInstance(typeTask), CHANGE_TASK)
                .commit()
    }

    override fun saveTask(task: Task, fragment: Fragment, typeTask: TaskType) {
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .show(supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!)
            .commit()
        returnTask?.putTaskInRecycler(task, position)
        position = null
    }

    override fun changeTask(task: Task, position: Int, putTaskInRecycler: OnPutTaskInRecycler, typeTask: TaskType) {
        this.returnTask = putTaskInRecycler
        this.position = position
        supportFragmentManager.beginTransaction()
            .hide(supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!)
            .add(R.id.app_bar_main, ChangeTaskFragment.newInstance(task, typeTask), CHANGE_TASK)
            .commit()
    }

    companion object {
        const val CHANGE_TASK: String = "change_task"
        const val RETURN_TASK: String = "return_task"
        const val POSITION: String = "position"
    }
}