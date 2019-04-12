package com.wiki.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WikiArticleResponseModel : Serializable {

    @SerializedName("query")
    var query: PagesModel? = null

    class PagesModel: Serializable {
        var pages: HashMap<String, ArticleModel>? = null
    }

    class ArticleModel : Serializable {

        @SerializedName("pageid")
        var pageId: Int? = null

        @SerializedName("ns")
        var ns: Int? = null

        @SerializedName("title")
        var title: String? = null
    }
}