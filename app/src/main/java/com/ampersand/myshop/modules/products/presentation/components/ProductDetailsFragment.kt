package com.ampersand.myshop.modules.products.presentation.components

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ampersand.myshop.R
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.presentation.adapter.ProductPropertiesAdapter
import com.google.gson.Gson

class ProductDetailsFragment:Fragment() {
    lateinit var recycler_view: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.product_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productJson = arguments?.getString("product") ?: return
        val product:ProductModel = Gson().fromJson(productJson, ProductModel::class.java)
        displayDetails(product,view)
    }

    fun displayDetails(product: ProductModel,rootView: View) {
        val tvProductName = rootView.findViewById<TextView>(R.id.tvProductName)
        tvProductName.text = product.name
        recycler_view = rootView.findViewById(R.id.productPropetiesRecyclerView)
        val adapter = ProductPropertiesAdapter(context)
        recycler_view.adapter = adapter
        adapter.setList(product.data)
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = manager
    }
}