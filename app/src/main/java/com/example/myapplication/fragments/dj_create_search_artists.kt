package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DJSearchSongsResultsAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.Playlist_page_adapter
import com.example.myapplication.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [dj_create_search_artists.newInstance] factory method to
 * create an instance of this fragment.
 */
class dj_create_search_artists : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dj_create_search_artists, container, false)

        val bundle = arguments
        val insertedText = bundle?.getString("searched").toString().toLowerCase()

        val posts: ArrayList<String> = ArrayList()
        val imageurl: ArrayList<String> = ArrayList()

        var database = FirebaseDatabase.getInstance().reference

        var getdata = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val songs: ArrayList<String> = ArrayList()
                for (i in snapshot.children ) {
                    if ((i.key.toString()).contains("song")) {
                        var name = i.child("Artist").value.toString().toLowerCase()
                        if (name.contains(insertedText)) {
                            songs.add(i.key.toString())
                        }
                    }
                }

                for (i in songs) {
                    var songName = snapshot.child(i).child("Name").value.toString()
                    var songArtist = snapshot.child(i).child("Artist").value.toString()
                    var caption: String = "$songName - $songArtist"
                    posts.add(caption)
                    var image = snapshot.child(i).child("ImageURL").value.toString()
                    imageurl.add(image)
                }

                val mRecyclerView: RecyclerView
                mRecyclerView = view.findViewById(R.id.recyclerView_results_artists_dj)
                mRecyclerView.layoutManager = LinearLayoutManager(activity as MainActivity, RecyclerView.VERTICAL, false)
                mRecyclerView.adapter= DJSearchSongsResultsAdapter(songs, posts, imageurl, activity as MainActivity)
            }
        }
        database.addValueEventListener(getdata)

        val editText = view.findViewById<EditText>(R.id.searchbar_artists_dj)

        editText.setText((activity as MainActivity).searchtext)

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var helpbundle = arguments
        var txt = helpbundle?.getString("searched").toString()

        view.findViewById<Button>(R.id.song_button2_dj).setOnClickListener {
            var bundle = Bundle()
            bundle.putString("searched", txt)
            val search = dj_create_search_songs()
            search.arguments = bundle
            (activity as MainActivity).makeCurrentFragment(search)
        }

        view.findViewById<Button>(R.id.artists_button2_dj).setOnClickListener {
            var bundle = Bundle()
            bundle.putString("searched", txt)
            val search = dj_create_search_artists()
            search.arguments = bundle
            (activity as MainActivity).makeCurrentFragment(search)
        }

        view.findViewById<Button>(R.id.playlists_button2_dj).setOnClickListener {
            var bundle = Bundle()
            bundle.putString("searched", txt)
            val search = dj_create_search_playlists()
            search.arguments = bundle
            (activity as MainActivity).makeCurrentFragment(search)
        }

        val showButton = view.findViewById<Button>(R.id.search_icon_artists_dj)
        val editText = view.findViewById<EditText>(R.id.searchbar_artists_dj)

        showButton.setOnClickListener {

            val txt = editText.text

            (activity as MainActivity).searchtext = txt.toString()

            var mytext = txt.toString()

            var bundle = Bundle()
            bundle.putString("searched", mytext)
            val results = dj_create_search_artists()
            results.arguments = bundle

            (activity as MainActivity).makeCurrentFragment(results)
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment dj_create_search_artists.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            dj_create_search_artists().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}