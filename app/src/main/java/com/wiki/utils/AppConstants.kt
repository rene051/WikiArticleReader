package com.wiki.utils

interface AppConstants {

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
        const val PROP = "prop"
        const val EX_INTRO = "exintro"
        const val EX_PLAIN_TEXT = "explaintext"
        const val TITLES = "titles"


        //Query values
        const val RANDOM = "random"
        const val JSON = "json"
        const val QUERY = "query"
        const val EXTRACT = "extracts"
        const val GRN_NAME_SPACE_VALUE = 0
        const val GRN_LIMIT_VALUE = 5
    }
}