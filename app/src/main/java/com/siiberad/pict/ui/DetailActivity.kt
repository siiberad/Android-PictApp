package com.siiberad.pict.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.siiberad.pict.R
import com.siiberad.pict.ui.adapter.SlideAdapter
import com.siiberad.pict.ui.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    companion object {
        fun show(activity: Activity, id: Int) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putInt("id", id)
                })
            }
            activity.startActivityForResult(intent, 91)
        }
    }

    lateinit var vm: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)
        vm = ViewModelProvider(this).get(DetailViewModel::class.java)
        val id = intent?.extras?.getInt("id")
        vm.fetch(id!!)
        observeViewModel()

    }

    private fun observeViewModel() {
        vm.listImage.observe(this, { data ->
            data?.let {
                val slideAdapter = SlideAdapter(this, it)
                vPackagerPager.apply {
                    invalidate()
                    adapter = slideAdapter
                    slideAdapter.notifyDataSetChanged()
                }
                vPackagerIndicator.setViewPager(vPackagerPager)
            }
        })
    }

}