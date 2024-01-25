package com.delliott.flickrbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.delliott.flickrbrowser.databinding.FragmentFirstBinding
import com.delliott.flickrbrowser.ui.PhotoAdapter
import com.delliott.flickrbrowser.ui.PhotosViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SEARCH_DELAY = 200L
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val photosViewModel: PhotosViewModel by viewModels()
    private val photoAdapter = PhotoAdapter()
    private var searchJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = photoAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        binding.searchBar.addTextChangedListener{
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(SEARCH_DELAY)
                val imagesList = photosViewModel.fetchImages(it.toString())
                photoAdapter.setPhotos(imagesList)
            }
        }

//        photosViewModel.photos.observe(viewLifecycleOwner) {
//            photoAdapter.setPhotos(it)
//        }
//
//        binding.searchBar.addTextChangedListener { editable ->
//            photosViewModel.fetchImages(editable.toString())
//        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}