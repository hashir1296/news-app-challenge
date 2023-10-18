package com.example.newsapp.presentation.newsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.example.newsapp.R
import com.example.newsapp.databinding.HolderHeadlineItemBinding
import com.example.newsapp.utils.ListViewType

class NewsListAdapter(
    private val context: Context,
    private val onClick: (NewsHeadlineDomainModel) -> Unit
) : PagingDataAdapter<NewsHeadlineDomainModel, NewsListAdapter.NewsItemViewHolder>(
    HEADLINE_ITEM_DIFF_CALLBACK
) {

    private var _listViewType: ListViewType = ListViewType.LINEAR

    inner class NewsItemViewHolder(private val binding: HolderHeadlineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsHeadlineDomainModel?) {
            item?.let { newsDomainModel ->
                if (newsDomainModel.imageURL.isNotEmpty()) {
                    val circularProgressDrawable =
                        CircularProgressDrawable(context)
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.start()

                    binding.ivHeadlineImage.load(newsDomainModel.imageURL) {
                        crossfade(true)
                        placeholder(circularProgressDrawable)
                    }
                }

                binding.tvHeadlineAuthor.text = newsDomainModel.author
                binding.tvHeadlinePublishedDate.text =
                    newsDomainModel.publishedDate
                binding.tvHeadlineTitle.text = newsDomainModel.title

                binding.itemView.setOnClickListener {
                    onClick.invoke(newsDomainModel)
                }

                //Set visibility

                when (_listViewType) {
                    ListViewType.LINEAR -> {
                        binding.ivHeadlineImage.visibility = View.VISIBLE
                    }

                    ListViewType.GRID -> {
                        binding.ivHeadlineImage.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun setListViewType(listViewType: ListViewType) {
        //Notifying adapter is a heavy operation so don't notify if the old value is same as new value

        if (_listViewType != listViewType){
            _listViewType = listViewType
            notifyItemRangeChanged(0, snapshot().items.size)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<HolderHeadlineItemBinding>(
            inflater, R.layout.holder_headline_item, parent, false
        )
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsItemViewHolder, position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val HEADLINE_ITEM_DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<NewsHeadlineDomainModel>() {
                override fun areItemsTheSame(
                    oldItem: NewsHeadlineDomainModel,
                    newItem: NewsHeadlineDomainModel
                ): Boolean {
                    return false
                }

                override fun areContentsTheSame(
                    oldItem: NewsHeadlineDomainModel,
                    newItem: NewsHeadlineDomainModel
                ): Boolean {
                    return false
                }
            }
    }
}
