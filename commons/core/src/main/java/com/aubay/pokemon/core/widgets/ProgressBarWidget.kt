package com.aubay.pokemon.core.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import com.aubay.pokemon.core.R
import com.aubay.pokemon.core.extensions.invisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.progress_bar_widget.view.*

class ProgressBarWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.progress_bar_widget, this, true)

        val arr: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ProgressBarWidget, defStyle, defStyleRes)

        if(arr.getDrawable(R.styleable.ProgressBarWidget_backgroundLoad) != null) setBackground(arr.getDrawable(R.styleable.ProgressBarWidget_backgroundLoad))

        setDimming(arr.getBoolean(R.styleable.ProgressBarWidget_dimming,true))

        setProgressBarImage(arr.getDrawable(R.styleable.ProgressBarWidget_file))

        setRotation()
    }

    private fun setProgressBarImage(imageFile: Drawable?) {
        Glide
            .with(context)
            .load(imageFile)
            .into(
                progressImg
            )
    }

    private fun setRotation() {

        val rotation = AnimationUtils.loadAnimation(context, R.anim.rotate)
        rotation.fillAfter = true
        progressImg.startAnimation(rotation)

    }

    private fun setDimming(dimming: Boolean) {
        if(!dimming) progBg.invisible()
    }
}