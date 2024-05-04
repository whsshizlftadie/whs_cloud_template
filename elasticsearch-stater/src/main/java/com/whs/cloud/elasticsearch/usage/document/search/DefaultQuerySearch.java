package com.whs.cloud.elasticsearch.usage.document.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;

public interface DefaultQuerySearch {

    SearchResponse querySourceDefault(SearchRequest request, QueryBuilder queryBuilders);
}
