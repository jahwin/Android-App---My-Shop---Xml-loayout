package com.ampersand.myshop.modules.products.presentation.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ampersand.myshop.R
import com.ampersand.myshop.modules.products.presentation.adapter.ProductListAdapter
import com.ampersand.myshop.modules.products.presentation.screens.ProductsViewModel

class ProductListFragment: Fragment(){
    lateinit var recycler_view: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.product_list_fragment, container, false)
        setupRecyclerView(rootView)
        initViewModel();
        return rootView
    }

    private fun setupRecyclerView(rootView: View) {
        recycler_view = rootView.findViewById(R.id.recycler_view)
        val adapter = ProductListAdapter(context)
        recycler_view.adapter = adapter
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = manager
    }

    private fun initViewModel() {
        val viewModel:ProductsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        viewModel.getProducts().observe(viewLifecycleOwner) {
            if (it != null) {
                (recycler_view.adapter as ProductListAdapter).setList(it)
            }else{
                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeApiCall()
    }

}