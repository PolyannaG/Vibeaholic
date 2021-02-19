package com.example.myapplication

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var detector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homepage=Homepage()
        val search=Search()
        val playing=Playing_now()
        val profile=Profile()
        val dj=DJ()

        makeCurrentFragment(homepage)


        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> makeCurrentFragment(homepage)
                R.id.ic_search -> makeCurrentFragment(search)
                R.id.ic_play_now -> makeCurrentFragment(playing)
                R.id.ic_profile -> makeCurrentFragment(profile)
                R.id.ic_dj->makeCurrentFragment(dj)

            }
            true
        }

    detector = GestureDetectorCompat(this, GestureListener())

       // val posts: ArrayList<String> = ArrayList()
       // for (i in 1..100){
       //     posts.add("Post # $i")
       // }
      // val mRecyclerView: RecyclerView

       // val view : View
        //view = View.inflate(R.layout.fragment_homepage, this, false)
        //mRecyclerView = view.findViewById(R.id.recyclerView)
      // mRecyclerView = findViewById<RecyclerView> (R.id.recyclerView);
        //mRecyclerView.layoutManager = LinearLayoutManager(this)
        //mRecyclerView.adapter= PostsAdapter(posts)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter=PostsAdapter(posts)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)){
            true
        }
        else {
            super.onTouchEvent(event)
        }
    }

    public fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener(){

        private val SWIPE_THESHOLD = 100
        private val SWIPE_VELOVITY_THRESHOLD = 100

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            var diffX=moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY=moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

           return if (Math.abs(diffX)>Math.abs(diffY)){
               //left or right swipe
                if (Math.abs(diffX)>SWIPE_THESHOLD && Math.abs(velocityX)>SWIPE_VELOVITY_THRESHOLD){
                    if (diffX>0) {
                        //right swipe
                        this@MainActivity.onSwipeRight()
                    }
                    else {
                        //right swipe
                        this@MainActivity.onSwipeLeft()
                    }
                    true
                }
                else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
            else{
                //up or down swipe
                if (Math.abs(diffY)>SWIPE_THESHOLD && Math.abs(velocityY)>SWIPE_VELOVITY_THRESHOLD){
                    if (diffY>0) {
                        //swipe down
                        this@MainActivity.onSwipeTop()
                    }
                    else {
                        //swipe up
                        this@MainActivity.onSwipeBottom()
                    }
                    true
                }
                else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }

            }


        }
    }


    private fun onSwipeBottom() {
        Toast.makeText(this, "Bottom swipe", Toast.LENGTH_LONG).show()
        makeCurrentFragment(Playlist())
    }

    private fun onSwipeTop() {
        Toast.makeText(this, "Top swipe", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeLeft() {
        Toast.makeText(this, "Left swipe", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeRight() {
        Toast.makeText(this, "Right swipe", Toast.LENGTH_LONG).show()
    }

}