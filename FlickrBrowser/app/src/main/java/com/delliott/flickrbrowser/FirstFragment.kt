package com.delliott.flickrbrowser

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.delliott.flickrbrowser.databinding.FragmentFirstBinding
import com.delliott.flickrbrowser.ui.PhotoAdapter
import com.delliott.flickrbrowser.ui.PhotosViewModel
import com.delliott.flickrbrowser.ui.RecyclerItemClickListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SEARCH_DELAY = 200L
class FirstFragment : Fragment(), RecyclerItemClickListener.OnRecyclerClickListener {

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
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        val context = requireContext()
        binding.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context, binding.recyclerView, this))

        binding.searchBar.addTextChangedListener{
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(SEARCH_DELAY)
               photosViewModel.fetchImages(it.toString())
            }
        }

        photosViewModel.photos.observe(viewLifecycleOwner, Observer {
            photoAdapter.setPhotos(it)
        })

        return binding.root
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d("test", "onItemClick: start")
        //Toast.makeText(context, "Normal tap at position $position", Toast.LENGTH_SHORT).show()
        val photo = photoAdapter.getPhoto(position)
        if (photo != null) {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(photo.url, photo.title, photo.owner)
            view.findNavController().navigate(action)
        }
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d("test", "onItemLongClick: start")
        Toast.makeText(context, "Long tap at position $position", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}