package com.wiki.data.api

import javax.inject.Inject

class NetworkApiImpl
@Inject
constructor(private val wikiDatabaseApi: WikiDatabaseApi) : NetworkApi {


}