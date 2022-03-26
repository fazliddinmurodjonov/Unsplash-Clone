package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adapters.ImagesAdapter
import com.example.androidconnectionlesson2.R
import com.example.androidconnectionlesson2.databinding.FragmentCategoryBinding
import com.example.retrofit.model.ImageModel
import com.example.retrofit.model.SearchModel
import com.example.retrofit.model.UrlModel
import com.example.retrofit.retrofit.Common
import com.example.retrofit.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment(R.layout.fragment_category) {
    private val binding: FragmentCategoryBinding by viewBinding()
    lateinit var retrofitService: RetrofitService
    lateinit var imagesAdapter: ImagesAdapter
    lateinit var list: ArrayList<ImageModel>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding)
        {
            val category = arguments?.getString(searchTab)
            list = ArrayList()
            imagesAdapter = ImagesAdapter(list)

            retrofitService = Common.retrofitService

            retrofitService.searchImages(category!!, 3, 30).enqueue(object : Callback<SearchModel> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                    if (response.isSuccessful) {
                        list.addAll(response.body()?.results!!)
                        imagesAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(requireContext(), "${response.code()}", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                override fun onFailure(call: Call<SearchModel>, t: Throwable) {

                }
            })
            rvFragment.adapter = imagesAdapter
            imagesAdapter.setOnImageClickListener {
                val image = bundleOf("image" to it.regular)
                findNavController().navigate(R.id.imageFragment, image)
            }
        }
    }

    companion object {
        private const val searchTab = "search_tab"

        @JvmStatic
        fun newInstance(str: String) = CategoryFragment().apply {
            arguments = Bundle().apply {
                putString(searchTab, str)
            }
        }
    }
}