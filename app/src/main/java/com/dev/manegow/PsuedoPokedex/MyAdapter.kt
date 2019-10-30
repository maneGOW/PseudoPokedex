package com.dev.manegow.PsuedoPokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.manegow.PseudoPokedex.R
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter (private val data: ArrayList<Results>, private val listener : Listener)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    interface Listener {
        fun OnItemClick(pokeData: Results)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], listener, position)
    }

    override fun getItemCount(): Int = data.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(pokeData: Results, listener: Listener, position: Int){
            itemView.setOnClickListener{listener.OnItemClick(pokeData)}
            itemView.text_name.text = pokeData.Name
        }
    }
}