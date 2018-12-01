package com.marcgdiez.moviedbapp.extensions

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.load(url: String) = Picasso.get().load(url).into(this)

fun ImageView.loadWithTransition(url: String, activity: AppCompatActivity) =
    Picasso.get()
        .load(url)
        .noFade()
        .into(this, object : Callback {
            override fun onSuccess() {
                activity.supportStartPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                activity.supportStartPostponedEnterTransition()
            }
        })