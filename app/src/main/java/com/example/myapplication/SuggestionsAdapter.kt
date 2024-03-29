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
import com.example.myapplication.fragments.Add_Suggest_song_page
import com.example.myapplication.fragments.Playing_now
import com.example.myapplication.fragments.Playlist
import com.squareup.picasso.Picasso

//used for user playlist and for search with recommendations

class SuggestionsAdapter(val songs: ArrayList<String>, val posts: ArrayList<String>, val imageurl: ArrayList<String>, val activity: MainActivity) :  RecyclerView.Adapter<SuggestionsAdapter.Viewholder>(){

    class Viewholder(itemView: View, activity: MainActivity) : RecyclerView.ViewHolder(itemView){
        val txt : TextView = itemView.findViewById(R.id.song_title_sug)
        val image : ImageView = itemView.findViewById(R.id.song_image_sug)
        val view1= itemView
        val activity1 = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionsAdapter.Viewholder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.suggestions_song_view, parent, false)
        return Viewholder(view , activity)
    }

    override fun getItemCount()=posts.size



    override fun onBindViewHolder(holder:SuggestionsAdapter.Viewholder, position: Int) {
        holder.txt.text = posts[position]
        Picasso.get().load(imageurl[position]).into(holder.image)

        val tv =holder.view1.findViewById<TextView>(R.id.song_title_sug);
        tv.isSelected = true;
        val view2 : CardView
        view2=holder.view1.findViewById((R.id.song_area_sug))
        view2.setOnClickListener {
            val bundle = Bundle()
            //var details = Call.Details()
            bundle.putString("song", posts[position])
            bundle.putString("image", imageurl[position])
            bundle.putString("songID", songs[position])

            val partyplaying=Add_Suggest_song_page()

            partyplaying.arguments=bundle

            activity.makeCurrentFragment(partyplaying)

        }
    }
}