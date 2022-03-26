package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidconnectionlesson2.R
import com.example.androidconnectionlesson2.databinding.FragmentImageBinding
import com.squareup.picasso.Picasso


class ImageFragment : Fragment(R.layout.fragment_image) {
    private val binding: FragmentImageBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding)
        {
            val image = arguments?.getString("image")
            Picasso.get().load(image).into(IV)

        }
    }
}