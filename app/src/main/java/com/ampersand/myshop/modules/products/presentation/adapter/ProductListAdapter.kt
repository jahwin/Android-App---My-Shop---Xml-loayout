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

class ProductListAdapter(private val context: Context?) :
RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list: List<ProductModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ProductModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.setData(current, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0
        lateinit var current: ProductModel

        fun setData(current: ProductModel, position: Int) {
            itemView.findViewById<TextView>(R.id.tvProductName).text = current.name
            this.pos = position
            this.current = current
        }

        fun setListeners() {
            itemView.setOnClickListener {
                val myCommunicator = context as MyCommunicator
                myCommunicator.displayDetails(current)
            }
        }
    }
}