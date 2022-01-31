package com.example.mvvmproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmproject.model.Blog
import java.util.ArrayList

class MainViewModel: ViewModel() {
    var list = MutableLiveData<ArrayList<Blog>>()
    private var newList = arrayListOf<Blog>()

    fun add(blog: Blog) {
        newList.add(0,blog)
        list.value = newList
    }

    fun remove(blog: Blog) {
        newList.remove(blog)
        list.value = newList
    }
}