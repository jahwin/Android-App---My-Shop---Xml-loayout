package com.ampersand.myshop.modules.products.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ampersand.myshop.R
import com.ampersand.myshop.modules.products.domain.model.MyCommunicator
import com.ampersand.myshop.modules.products.domain.model.ProductModel

class ProductPropertiesAdapter(private val context: Context?) :
RecyclerView.Adapter<ProductPropertiesAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.product_property_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: Map<String, Any>?) {
        this.list = list?.map { "${it.key}: ${it.value}" } ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0
        lateinit var current: String

        fun setData(current: String, position: Int) {
            itemView.findViewById<TextView>(R.id.tvProductProperty).text = current
            this.pos = position
            this.current = current
        }
    }
}