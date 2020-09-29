package com.siiberad.pict.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.siiberad.pict.R
import com.siiberad.pict.model.Result
import com.siiberad.pict.ui.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    lateinit var vm: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)
        vm = ViewModelProvider(this).get(SplashViewModel::class.java)
        vm.getSources()
        observeViewModel()
    }

    private fun observeViewModel() {
        vm.action.observe(this, {
            when (it) {
                is Result.Success -> {
                    if (vm.loggedIn()) MainActivity.show(this@SplashActivity) else LoginActivity.show(
                        this@SplashActivity
                    )
                }
                is Result.Failure -> vm.getSources()
                Result.InProgress -> ""
            }
        })
    }
}