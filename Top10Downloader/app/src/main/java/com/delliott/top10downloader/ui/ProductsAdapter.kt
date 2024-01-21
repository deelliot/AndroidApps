package com.delliott.top10downloader.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delliott.top10downloader.R
import com.delliott.top10downloader.data.Product
import com.squareup.picasso.Picasso

class ProductImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.name)
    var description: TextView = view.findViewById(R.id.description)
    var rating: TextView = view.findViewById(R.id.rating)
    var price: TextView = view.findViewById(R.id.price)
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
}

class ProductsAdapter() :
    RecyclerView.Adapter<ProductImageViewHolder>() {
    private val TAG = "productAdapter"
    private var productsList: List<Product> = emptyList()

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount called")
        return productsList.size
    }

    fun setProducts(newProducts: List<Product>) {
        Log.d(TAG, "hello")
        productsList = newProducts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        //called by layout manager when it wants new data in an existing vew
        val productItem = productsList[position]
        Log.d(TAG, "onBindingViewHolder: ${productItem.name} --> $position")

        Picasso.get().load(productItem.thumbnail)
            .error(R.drawable.placeholder)
            .placeholder((R.drawable.placeholder))
            .into(holder.thumbnail)

        holder.name.text = productItem.name
        holder.description.text = productItem.description
        holder.rating.text = productItem.rating.toString()
        holder.price.text = "$" + productItem.price.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        //called by layout manager when it needs a new view
        Log.d(TAG, "onCreate ViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductImageViewHolder(view)
    }

}