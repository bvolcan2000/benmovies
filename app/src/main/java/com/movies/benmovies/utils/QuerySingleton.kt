package com.movies.benmovies.utils

import com.movies.benmovies.repository.SearchRepository

class QuerySingleton constructor() {
    companion object {
        var instance: QuerySingleton = QuerySingleton()
            private set
    }
    var query:String =""
}