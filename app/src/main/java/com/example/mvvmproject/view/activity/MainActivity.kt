package com.example.mvvmproject.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.ActivityMainBinding
import com.example.mvvmproject.model.Blog
import com.example.mvvmproject.view.adapter.BlogRecyclerAdapter
import com.example.mvvmproject.viewmodel.MainViewModel
import com.example.mvvmproject.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var view: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        view.addButton.setOnClickListener {
            addData()
        }
        initializeAdapter()
    }

    private fun initializeAdapter() {
        view.recyclerView.layoutManager = LinearLayoutManager(this)
        observeData()
    }

    private fun observeData() {
        viewModel.list.observe(this) {
            view.recyclerView.adapter = BlogRecyclerAdapter(viewModel, it)
        }
    }

    private fun addData() {
        val title = view.inputValue.text.toString()
        if (title.isNotEmpty()) {
            val blog = Blog(title)
            viewModel.add(blog)
            view.inputValue.text.clear()
            view.recyclerView.adapter?.notifyItemInserted(0)
        }
    }
}