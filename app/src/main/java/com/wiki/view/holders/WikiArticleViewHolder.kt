package com.wiki.view.holders

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_wiki_random_article.view.*

class WikiArticleViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var wikiMainRelative: RelativeLayout = itemView.wikiMainRelative
    var wikiCardView: CardView = itemView.wikiTitleItemCard
    var wikiTitle: TextView = itemView.wikiTitleText
}