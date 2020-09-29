package com.siiberad.pict.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.siiberad.pict.R
import com.siiberad.pict.utils.Util
import kotlinx.android.synthetic.main.item_slider.view.*


class SlideAdapter(var context: Context, var images: List<String?>) : PagerAdapter() {
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater =
            context.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_slider, container, false)
        Util.loadImage(view.imgSlider, images[position])
        (container as ViewPager).addView(view, null)
        return view
    }


}