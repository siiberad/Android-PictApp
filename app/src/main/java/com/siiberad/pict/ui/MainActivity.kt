package com.siiberad.pict.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.siiberad.pict.R
import com.siiberad.pict.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun show(source: Activity) {
            source.startActivity(Intent(source, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
        }
    }

    lateinit var mvm: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        mvm = ViewModelProvider(this).get(MainViewModel::class.java)
        mvm.fetch()
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        txt_toolbar.text = "Pict App"
    }


    override fun onBackPressed() {
        finishAffinity()
    }
}