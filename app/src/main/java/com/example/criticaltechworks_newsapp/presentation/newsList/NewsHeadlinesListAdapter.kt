package com.example.criticaltechworks_newsapp.presentation.newsList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.example.criticaltechworks_newsapp.R
import com.example.criticaltechworks_newsapp.databinding.HolderHeadlineItemBinding

class NewsHeadlinesListAdapter(
    private val context: Context,
    private val onClick: (NewsHeadlineDomainModel) -> Unit
) : PagingDataAdapter<NewsHeadlineDomainModel, NewsHeadlinesListAdapter.NewsItemViewHolder>(
    HEADLINE_ITEM_DIFF_CALLBACK
) {

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
            }
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
