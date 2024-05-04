package com.whs.cloud.elasticsearch.usage.document.search;


import org.elasticsearch.search.SearchHits;

import java.util.List;

public interface HitsConvert<T> {

    List<T> convertList(SearchHits hits, Class clazz);


}
