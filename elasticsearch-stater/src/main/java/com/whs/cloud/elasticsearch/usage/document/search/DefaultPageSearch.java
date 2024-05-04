package com.whs.cloud.elasticsearch.usage.document.search;

import com.whs.cloud.elasticsearch.usage.document.search.page.ESPage;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

public interface DefaultPageSearch {

    ESPage pageSearch(SearchRequest searchRequest, QueryBuilder queryBuilder, int page, int size, Class clazz, String orderField, SortOrder sortOrder);
}
