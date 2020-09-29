package com.siiberad.pict.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.siiberad.pict.R
import com.siiberad.pict.model.PictModel
import com.siiberad.pict.ui.DetailActivity
import com.siiberad.pict.ui.adapter.HomeAdapter
import com.siiberad.pict.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var vm: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (activity != null) {
            vm = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.sourcesItem.observe(viewLifecycleOwner, {
            setAdapter(it)
        })
    }

    private fun setAdapter(data: List<PictModel>?) {
        val homeAdapter = HomeAdapter(data!!)
        homeAdapter.mOnItemClickListener = object : HomeAdapter.OnItemClickListener {
            override fun onClick(id: Int?) {
                DetailActivity.show(activity!!, id!!)
            }
        }
        rv_home.apply {
            layoutManager = LinearLayoutManager(context)
            homeAdapter.notifyDataSetChanged()
            adapter = homeAdapter
        }
    }
}