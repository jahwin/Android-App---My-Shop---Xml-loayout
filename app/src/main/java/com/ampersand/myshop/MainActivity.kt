package com.ampersand.myshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ampersand.myshop.modules.products.domain.model.MyCommunicator
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.presentation.components.ProductDetailsFragment
import com.ampersand.myshop.modules.products.presentation.screens.ProductDetailsActivity
import com.ampersand.myshop.util.EventBus
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), MyCommunicator {
    private var mIsDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val fragmentBView = findViewById<View>(R.id.ProductDetailsFragment)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(product: ProductModel) {
        if (mIsDualPane) { // If we are in Tablet
            val productDetailsFragment = supportFragmentManager.findFragmentById(R.id.ProductDetailsFragment) as ProductDetailsFragment?
            val rootView = productDetailsFragment?.view;
            if (rootView != null) {
                productDetailsFragment.displayDetails(product,rootView)
            }
        } else { // When we are in Smart phone
            val intent = Intent(this, ProductDetailsActivity::class.java)
            val gson = Gson()
            val productJson = gson.toJson(product)
            intent.putExtra("product", productJson)
            startActivity(intent)
        }
    }
}
