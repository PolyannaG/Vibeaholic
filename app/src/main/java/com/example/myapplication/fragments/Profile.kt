package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.PlaylistAdapter
import com.example.myapplication.R
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val playlists: ArrayList<String> = ArrayList()
        for (i in 1..100){
            playlists.add("Playlist # $i")
        }
        val mRecyclerView2: RecyclerView
        mRecyclerView2 = view.findViewById(R.id.recyclerView_profile)
        mRecyclerView2.layoutManager = LinearLayoutManager(activity as MainActivity, RecyclerView.HORIZONTAL, false)
        mRecyclerView2.adapter= PlaylistAdapter(playlists, activity as MainActivity)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<ImageView>(R.id.profile_image1).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }
        view.findViewById<ImageView>(R.id.profile_image2).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }
        view.findViewById<ImageView>(R.id.profile_image3).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }
        /*
        view.findViewById<ImageView>(R.id.profile_image4).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }
        view.findViewById<ImageView>(R.id.profile_image5).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }
        view.findViewById<ImageView>(R.id.profile_image6).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }
        view.findViewById<ImageView>(R.id.profile_image7).setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(Playlist())
        }


        */
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}