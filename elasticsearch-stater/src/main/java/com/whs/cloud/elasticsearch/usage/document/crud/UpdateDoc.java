package com.whs.cloud.elasticsearch.usage.document.crud;

public interface UpdateDoc {

    Boolean updateDoc(String indexName,String id,Object data);
}
