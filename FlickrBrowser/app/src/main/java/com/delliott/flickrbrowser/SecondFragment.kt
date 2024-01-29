package com.delliott.flickrbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.delliott.flickrbrowser.databinding.FragmentSecondBinding
import com.squareup.picasso.Picasso

class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoUrl = args.photoUrl
        Picasso.get()
            .load(photoUrl)
            .error(R.drawable.placeholder)
            .into(binding.imageDetailed)

        binding.title.text = args.photoTitle
        binding.author.text = args.photoAuthor
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}