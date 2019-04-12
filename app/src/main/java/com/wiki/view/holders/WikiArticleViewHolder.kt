package com.wiki.view.holders

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_wiki_random_article.view.*

class WikiArticleViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var wikiMainRelative: RelativeLayout = itemView.wikiMainRelative
    var wikiCardView: CardView = itemView.wikiTitleItemCard
    var wikiTitle: TextView = itemView.wikiTitleText
}