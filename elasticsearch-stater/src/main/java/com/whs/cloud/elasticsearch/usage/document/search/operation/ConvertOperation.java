package com.whs.cloud.elasticsearch.usage.document.search.operation;

import com.alibaba.fastjson.JSON;
import com.whs.cloud.elasticsearch.usage.document.search.HitsConvert;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;

public class ConvertOperation<T> implements HitsConvert {

    @Override
    public List<T> convertList(SearchHits hits, Class clazz) {
        List<T> result = new ArrayList<>();
        for (SearchHit hit : hits) {
            Object object = JSON.parseObject(hit.getSourceAsString(), clazz);
            result.add((T) object);
        }
        return result;
    }


}
