package com.example.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adapters.CategoryAdapter
import com.example.adapters.ImagesAdapter
import com.example.androidconnectionlesson2.R
import com.example.androidconnectionlesson2.databinding.FragmentMenuBinding
import com.example.androidconnectionlesson2.databinding.ItemTabLayoutBinding
import com.example.retrofit.model.ImageModel
import com.example.retrofit.model.SearchModel
import com.example.retrofit.retrofit.Common
import com.example.retrofit.retrofit.RetrofitService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val binding: FragmentMenuBinding by viewBinding()
    private lateinit var categoryList: ArrayList<String>
    lateinit var categoryAdapter: CategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryList = ArrayList()
        categoryList = ArrayList(listOf(*resources.getStringArray(R.array.category_array)))

        categoryAdapter = CategoryAdapter(categoryList, requireActivity())
        binding.viewPager.adapter = categoryAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
        }.attach()
        ControlIndicator()
        setTab()
    }

    private fun ControlIndicator() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabView =
                    ItemTabLayoutBinding.inflate(LayoutInflater.from(requireContext()), null, false)
                tab!!.customView = null
                tabView.tabLayoutText.text = categoryList[tab.position]
                tabView.tabLayoutText.setTextColor(Color.WHITE)
                tabView.indicator.visibility = View.VISIBLE
                tab?.customView = tabView.root

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabView =
                    ItemTabLayoutBinding.inflate(LayoutInflater.from(requireContext()), null, false)
                tab!!.customView = null
                tabView.tabLayoutText.text = categoryList[tab.position]
                tabView.indicator.visibility = View.INVISIBLE
                tab?.customView = tabView.root

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


        })
    }

    private fun setTab() {
        val tabCount = binding.tabLayout.tabCount
        for (i in 0 until tabCount) {
            val tabView =
                ItemTabLayoutBinding.inflate(LayoutInflater.from(requireContext()), null, false)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = tabView.root

            tabView.tabLayoutText.text = categoryList[i]
            if (i == 0) {
                tabView.tabLayoutText.setTextColor(Color.WHITE)
                tabView.indicator.visibility = View.VISIBLE
            } else {
                tabView.indicator.visibility = View.INVISIBLE
            }
        }
    }
}