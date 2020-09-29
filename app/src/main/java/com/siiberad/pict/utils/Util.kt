package com.siiberad.pict.utils

import android.content.Context
import android.os.Build
import android.util.Patterns
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.siiberad.pict.R


class Util {
    companion object {
        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.toRegex().matches(email);
        }

        fun isLollipopOrAbove(): Boolean {
            return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        }

        private fun getProgressDrawable(context: Context): CircularProgressDrawable {
            return CircularProgressDrawable(context).apply {
                strokeWidth = 10f
                centerRadius = 50f
                start()
            }
        }

        fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
            val options = RequestOptions()
                .placeholder(progressDrawable)
            Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(uri)
                .into(this)
        }

        fun loadImage(view: ImageView, url: String?) {
            view.loadImage(url, getProgressDrawable(view.context))
        }
    }
}