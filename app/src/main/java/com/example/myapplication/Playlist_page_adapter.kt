package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.Playing_now
import com.example.myapplication.fragments.Playlist

class Playlist_page_adapter(val posts: ArrayList<String>, val activity: MainActivity) :  RecyclerView.Adapter<Playlist_page_adapter.Viewholder>(){

    class Viewholder(itemView: View, activity: MainActivity) : RecyclerView.ViewHolder(itemView){
        val txt : TextView = itemView.findViewById(R.id.song_title)
        val view1= itemView
        val activity1 = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Playlist_page_adapter.Viewholder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.playlist_song, parent, false)
        return Viewholder(view , activity)
    }

    override fun getItemCount()=posts.size



    override fun onBindViewHolder(holder:Playlist_page_adapter.Viewholder, position: Int) {
        holder.txt.text = posts[position]

        val view2 : CardView
        view2=holder.view1.findViewById((R.id.song_area))
        view2.setOnClickListener {
            val bundle = Bundle()
            //var details = Call.Details()
            bundle.putString("song", posts[position])
           val playing=Playing_now()
            playing.arguments=bundle

            activity.makeCurrentFragment(playing)
            //activity.findViewById<TextView>(R.id.song_title_playing).text = "posts[position]"


        }
    }
}