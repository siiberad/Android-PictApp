package com.siiberad.pict.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siiberad.pict.R
import com.siiberad.pict.model.PictModel
import com.siiberad.pict.utils.Util
import kotlinx.android.synthetic.main.item_rv.view.*


class HomeAdapter(private val data: List<PictModel>) :
    RecyclerView.Adapter<HomeAdapter.RvHolder>() {
    var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(id: Int?)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): RvHolder {
        return RvHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_rv, viewGroup, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onClick(data[position].id)
        }
    }

    class RvHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(pictModel: PictModel) {
            Util.loadImage(itemView.img_bg, pictModel.url?.url1)
//            Glide.with(itemView.context)
//                .load(pictModel.url?.url1)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .into(itemView.img_bg)
            itemView.txt_desc.text = pictModel.title
        }
    }
}

