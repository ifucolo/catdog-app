package example.com.catdogapp.feature.home.dogs.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import example.com.catdogapp.R
import example.com.catdogapp.extensions.inflate
import example.com.catdogapp.feature.home.dogs.domain.entity.DogDetail
import kotlinx.android.synthetic.main.item_common.view.*

class DogAdapter constructor(val dogs: List<DogDetail>, val listener: (DogDetail) -> Unit): RecyclerView.Adapter<DogAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dogDetail = dogs[position]

        Glide.with(viewHolder.itemView)
                .load(dogDetail.imgUrl)
                .into(viewHolder.itemView.img)

        viewHolder.itemView.setOnClickListener {
            listener.invoke(dogDetail)
        }
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_common))

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(p0)
    override fun getItemCount() = dogs.count()

}
