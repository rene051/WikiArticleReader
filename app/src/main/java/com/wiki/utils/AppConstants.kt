package com.wiki.utils

class AppConstants {

    companion object {
        //URL
        const val BASE_URL = "https://en.wikipedia.org"
        const val API_BASE_URL = "/w/api.php?"

        //Query
        const val FORMAT = "format"
        const val ACTION = "action"
        const val GENERATOR = "generator"
        const val GRN_NAME_SPACE = "grnnamespace"
        const val GRN_LIMIT = "grnlimit"

        //Query values
        const val RANDOM = "random"
        const val JSON = "json"
        const val QUERY = "query"
        const val GRN_NAME_SPACE_VALUE = 0
        const val GRN_LIMIT_VALUE = 5
    }
}