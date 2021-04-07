package netgem.fr.gify.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.git_item.view.*
import netgem.fr.gify.R
import netgem.fr.gify.network.data.GifModel
import netgem.fr.gify.ui.FullScreenGifFragment
import netgem.fr.gify.utils.FULL_SCREEN_URL
import netgem.fr.gify.utils.SEARCH_KEY
import netgem.fr.gify.utils.replaceFragment

class GifAdapter(private val fragment: Fragment, private val onItemClick: (String) -> Unit): PagedListAdapter<GifModel, GifAdapter.GifsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.git_item, parent, false)
        return GifsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class GifsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(gifModel: GifModel?) {
            Glide.with(fragment)
                .load(gifModel?.images?.fixed_height?.url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(itemView.gifImage)

            itemView.gifItemTitle.text = gifModel?.title

            itemView.setOnClickListener {
                gifModel?.url?.let { it -> onItemClick(it) }
            }
        }
    }

    private class DiffCallback: DiffUtil.ItemCallback<GifModel>() {
        override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel): Boolean {
            return true
        }

    }
}