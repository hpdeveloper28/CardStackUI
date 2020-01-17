package com.myaccountanimation.customviews

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.myaccountanimation.recyclerviewdemo.R

class CustomTextLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var characterAnimationTime = 100
    private var textSize = 22f
    private var letterSpacing = 0f
    private var animationDuration = 2000L

    init {
        orientation = HORIZONTAL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextLayout, defStyleAttr, 0)
        textSize = typedArray.getFloat(R.styleable.CustomTextLayout_textSize, textSize)
        typedArray.recycle()
    }

    /**
     * This function sets the animated alpha text
     * @param context Context of Activity / Fragment
     * @param text Text string
     * @param initialDelay Start animation delay
     */
    fun setAnimatedText(context: Context, text: String, initialDelay: Long = 0) {
        var textDrawPosition = 0
        Handler().postDelayed({
            for (char in text) {
                val textView = getTextView(char.toString())
                textView.visibility = View.GONE
                this.addView(textView)
                textDrawPosition++
                drawAnimatedText(
                    context,
                    this,
                    textView,
                    textDrawPosition,
                    text,
                    (textDrawPosition * characterAnimationTime).toLong()
                )
            }
        }, initialDelay)
    }

    private fun drawAnimatedText(
        context: Context,
        parentView: LinearLayoutCompat,
        textView: AppCompatTextView,
        position: Int,
        text: String,
        initialDelay: Long
    ) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.WHITE, Color.BLACK)
        colorAnimation.startDelay = initialDelay
        colorAnimation.duration = animationDuration
        colorAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                textView.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animator: Animator) {
                if (position == text.length) {
                    val updatedTextView = getTextView(text)
                    updatedTextView.setTextColor(Color.BLACK)
                    updatedTextView.visibility = View.VISIBLE
                    parentView.removeAllViews()
                    parentView.addView(updatedTextView)
                }
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        colorAnimation.addUpdateListener {
            textView.setTextColor(it.animatedValue as Int)
        }
        colorAnimation.start()
    }

    private fun getTextView(text: String): AppCompatTextView {
        val textView = AppCompatTextView(context)
        textView.text = text
        textView.textSize = textSize
        textView.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC)
        textView.letterSpacing = letterSpacing
        return textView
    }
}