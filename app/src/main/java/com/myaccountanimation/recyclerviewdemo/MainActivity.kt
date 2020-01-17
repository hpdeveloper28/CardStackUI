package com.myaccountanimation.recyclerviewdemo
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val text1 = "welcome back"
    private val text2 = "mr hiren patel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textLayoutFirst.setAnimatedText(this, text1)
        textLayoutSecond.setAnimatedText(this, text2, 200)

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


        val animMyMessageView: Animation = AnimationUtils.loadAnimation(
            this@MainActivity,
            R.anim.bottom_to_original
        )
        animMyMessageView.startOffset = 500

        val animMyAccount: Animation = AnimationUtils.loadAnimation(
            this@MainActivity,
            R.anim.bottom_to_original_one
        )
        animMyAccount.startOffset = 500

        parentMyAccounts.animation = animMyMessageView
        txtMyMessage.animation = animMyAccount

    }
}
