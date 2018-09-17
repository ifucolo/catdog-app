package example.com.catdogapp.feature.home.tabs.cats.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import example.com.catdogapp.R
import example.com.catdogapp.utill.extensions.inflate
import example.com.catdogapp.feature.home.tabs.cats.domain.entity.CatDetail
import kotlinx.android.synthetic.main.item_common.view.*

class CatAdapter constructor(val cats: List<CatDetail>, val listener: (CatDetail) -> Unit): RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val catDetail = cats[position]

        Glide.with(viewHolder.itemView)
                .load(catDetail.imgUrl)
                .into(viewHolder.itemView.img)

        viewHolder.itemView.setOnClickListener {
            listener.invoke(catDetail)
        }
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_common))

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(p0)
    override fun getItemCount() = cats.count()

}
