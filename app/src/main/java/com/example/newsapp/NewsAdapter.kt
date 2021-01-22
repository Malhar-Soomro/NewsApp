package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listener:ItemsClicked): RecyclerView.Adapter<ViewHolder>() {

    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_items,parent,false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.authorText.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.myImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updateNews:ArrayList<News>){
        items.clear()
        items.addAll(updateNews)

        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView:TextView = itemView.findViewById(R.id.title)
    val myImage:ImageView = itemView.findViewById(R.id.img)
    val authorText:TextView = itemView.findViewById(R.id.author)

}
interface ItemsClicked{
    fun onItemClicked(item:News)


}