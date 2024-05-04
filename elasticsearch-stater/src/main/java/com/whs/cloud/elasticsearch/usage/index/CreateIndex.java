package com.whs.cloud.elasticsearch.usage.index;

import com.whs.cloud.elasticsearch.usage.index.definition.IndexDefinition;

import java.util.Map;

public interface CreateIndex {

    void createIndexJsonString(String indexName,String mapping);

    void createIndexMap(String indexName, Map<String, IndexDefinition> mapping);
}
