package com.example.mvvmproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.databinding.ItemsLayoutBinding
import com.example.mvvmproject.model.Blog
import com.example.mvvmproject.viewmodel.MainViewModel
import java.util.ArrayList

class BlogRecyclerAdapter(val viewModel: MainViewModel, val arrayList: ArrayList<Blog>): RecyclerView.Adapter<BlogRecyclerAdapter.VH>() {
    inner class VH(private val view: ItemsLayoutBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(blog: Blog) {
            view.inputText.text = blog.title
            view.deleteButton.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}