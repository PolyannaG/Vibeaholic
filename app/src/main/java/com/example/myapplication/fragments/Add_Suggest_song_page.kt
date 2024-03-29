package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Add_Suggest_song_page.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_Suggest_song_page : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_add__suggest_song_page, container, false)

        val bundle=arguments
        val sngtxt=view.findViewById<TextView>(R.id.song_title_playing_party)
        val img = view.findViewById<ImageView>(R.id.imageView_party)
        val url = bundle?.getString("image")
        val song = bundle?.getString("song")
        Picasso.get().load(url).into(img)
        sngtxt.text = song

        if ((activity as MainActivity).onCreate){
            val but=view.findViewById<Button>(R.id.addorsuggestbutton)
            but.text="Add"
        }
        else {
            val but=view.findViewById<Button>(R.id.addorsuggestbutton)
            but.text="Suggest"
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.addorsuggestbutton).setOnClickListener {
            if ((activity as MainActivity).onCreate) {
                val but = view.findViewById<Button>(R.id.addorsuggestbutton)
                if (but.text == "Add") {
                    but.text = "Cancel"
                } else {
                    but.text = "Add"
                }
            } else {
                val but = view.findViewById<Button>(R.id.addorsuggestbutton)
                if (but.text == "Suggest") {
                    but.text = "Cancel"
                } else {
                    but.text = "Suggest"
                }
            }
        }

        view.findViewById<Button>(R.id.play_button_party).setOnClickListener {
            if ((activity as MainActivity).onDj)
                Toast.makeText(activity, "You can't hear a song on DJ mode!", Toast.LENGTH_SHORT).show()
                }

        view.findViewById<Button>(R.id.details_party).setOnClickListener {
            val bundle=arguments
            val id = bundle?.getString("songID")
            val newbundle = Bundle()
            newbundle.putString("songID", id.toString())
            val details = Song_details()
            details.arguments = newbundle
            (activity as MainActivity).makeCurrentFragment(details)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Add_Suggest_song_page.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Add_Suggest_song_page().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}