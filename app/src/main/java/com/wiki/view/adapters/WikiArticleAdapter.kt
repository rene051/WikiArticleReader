package com.wiki.view.adapters

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wiki.R
import com.wiki.data.models.WikiArticleResponseModel
import com.wiki.utils.helpers.ScreenHelper.Companion.setViewLayoutParams
import com.wiki.view.holders.WikiArticleViewHolder

class WikiArticleAdapter(private var activity: Activity,
    private var wikiArticleList: ArrayList<WikiArticleResponseModel.ArticleModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WikiArticleViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_wiki_random_article, parent, false))
    }

    override fun getItemCount(): Int {
        return wikiArticleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = wikiArticleList[position]
        holder as WikiArticleViewHolder

        holder.wikiTitle.text = item.title

        setViewLayoutParams(activity, holder.wikiMainRelative, 0.8, 0.2)
    }

}