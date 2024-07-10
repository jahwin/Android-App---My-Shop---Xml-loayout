package com.ampersand.myshop.modules.products.presentation.screens

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arrow.core.raise.Null
import com.ampersand.myshop.R
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.presentation.components.ProductDetailsFragment
import com.google.gson.Gson

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details_screen)
        val productDetailsFragment = supportFragmentManager.findFragmentById(R.id.ProductDetails) as ProductDetailsFragment?
        val productJson = intent.getStringExtra("product")
        if (productJson == null) {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
        }else {
            val product: ProductModel = Gson().fromJson(productJson, ProductModel::class.java)
            // set the product details as arguments to the fragment
            val bundle = Bundle()
            bundle.putString("product", productJson)
            productDetailsFragment?.arguments = bundle
        }

    }
}