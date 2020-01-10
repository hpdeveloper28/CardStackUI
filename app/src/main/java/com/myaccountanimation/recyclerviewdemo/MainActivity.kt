package com.myaccountanimation.recyclerviewdemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.graphics.minus
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = ArrayList<String>()
        data.add("1")
        data.add("2")
        data.add("3")
        data.add("4")
        data.add("5")
        data.add("6")
        data.add("7")

        val recyclerAdapter = MyRecyclerViewAdapter(this@MainActivity, data)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = recyclerAdapter


        val animation: Animation = AnimationUtils.loadAnimation(
            this@MainActivity,
            R.anim.bottom_to_original
        )
        val animation1: Animation = AnimationUtils.loadAnimation(
            this@MainActivity,
            R.anim.bottom_to_original_one
        )

        parentMyAccounts.animation = animation
        txtMyMessage.animation = animation1

    }
}
