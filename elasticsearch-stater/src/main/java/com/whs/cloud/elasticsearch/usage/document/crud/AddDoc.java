package com.whs.cloud.elasticsearch.usage.document.crud;

public interface AddDoc {

    Boolean createDoc(String indexName,String id,Object data);
}
