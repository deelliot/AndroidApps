package com.delliott.top10downloader

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.delliott.top10downloader.databinding.ActivityMainBinding
import com.delliott.top10downloader.ui.ProductViewModel
import com.delliott.top10downloader.ui.ProductsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()
    private val productAdapter = ProductsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadProducts()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = productAdapter

        viewModel.products.observe(this) {
            productAdapter.setProducts(it)
        }

        viewModel.title.observe(this) {
            binding.header.text = viewModel.title.value
        }

        val allProducts = View.OnClickListener {
            viewModel.allProductsSelected()
        }
        binding.buttonAll.setOnClickListener(allProducts)

        val top3 = View.OnClickListener {
            viewModel.top3Selected()
        }
        binding.buttonTop3.setOnClickListener(top3)

        val top25 = View.OnClickListener {
            viewModel.top25Selected()
        }
        binding.buttonTop25.setOnClickListener(top25)
    }
}