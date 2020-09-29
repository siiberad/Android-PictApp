package com.siiberad.pict.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.siiberad.pict.R
import com.siiberad.pict.ui.LoginActivity
import com.siiberad.pict.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    lateinit var vm: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        initText()
        initClick()
    }

    private fun initText() {
        txt_name.text = "Muhammad Tedy Irwansyah"
        txt_email.text = vm.email
        txt_phone_number.text = "085326667013"
    }

    private fun initClick(){
        btn_logout.setOnClickListener {
            vm.logout()
            LoginActivity.show(requireActivity())
        }
    }

}