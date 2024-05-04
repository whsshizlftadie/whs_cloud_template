package com.whs.cloud.elasticsearch.usage.document.crud.opeartion;

import com.alibaba.fastjson.JSON;
import com.whs.cloud.elasticsearch.ex.ElasticSearchException;
import com.whs.cloud.elasticsearch.usage.document.crud.AddDoc;
import com.whs.cloud.elasticsearch.usage.document.crud.DeleteDoc;
import com.whs.cloud.elasticsearch.usage.document.crud.UpdateDoc;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

public class DocOperation implements AddDoc, DeleteDoc, UpdateDoc {

    private RestHighLevelClient restHighLevelClient;

    public DocOperation(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }


    @Override
    public Boolean createDoc(String indexName, String id, Object data) {
        try {
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(id);
            String jsonString = JSON.toJSONString(data);
            indexRequest.source(jsonString, XContentType.JSON);
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (response.status().getStatus() != RestStatus.OK.getStatus()) {
                return false;
            }
        } catch (Exception e) {
            throw new ElasticSearchException(e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean deleteDoc(String indexName, String id) {

        try {

            DeleteRequest deleteRequest = new DeleteRequest(indexName);
            deleteRequest.id(id);
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (response.status().getStatus() != RestStatus.OK.getStatus()) {
                return false;
            }
        } catch (Exception e) {
            throw new ElasticSearchException(e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean updateDoc(String indexName, String id, Object data) {

        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index(indexName).id(id).doc(JSON.toJSONString(data),XContentType.JSON);
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

            if (response.status().getStatus() != RestStatus.OK.getStatus()) {
                return false;
            }

        } catch (Exception e) {
            throw new ElasticSearchException(e.getMessage());
        }

        return true;
    }
}
