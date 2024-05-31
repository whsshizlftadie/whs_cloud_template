package com.whs.cloud.elasticsearch.usage.index.operation;

import com.whs.cloud.elasticsearch.ex.ElasticSearchException;
import com.whs.cloud.elasticsearch.usage.index.CreateIndex;
import com.whs.cloud.elasticsearch.usage.index.DeleteIndex;
import com.whs.cloud.elasticsearch.usage.index.GetIndexName;
import com.whs.cloud.elasticsearch.usage.index.definition.IndexDefinition;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

public class IndexOperation implements CreateIndex, DeleteIndex, GetIndexName {

    private final RestHighLevelClient restHighLevelClient;

    public IndexOperation(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public void createIndexJsonString(String indexName, String mapping) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.mapping(mapping, XContentType.JSON);
        try {
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new ElasticSearchException(e.getMessage());
        }
    }

    @Override
    public void createIndexMap(String indexName, Map<String, IndexDefinition> mapping) {
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.mapping(generateMapping(mapping));
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new ElasticSearchException(e.getMessage());
        }
    }

    private XContentBuilder generateMapping(Map<String, IndexDefinition> mapping) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            for (Map.Entry<String, IndexDefinition> entry : mapping.entrySet()) {
                {
                    builder.startObject(entry.getKey());
                    {
                        builder.field("type", entry.getValue().getType());
                        if (!StringUtils.isEmpty(entry.getValue().getAnalysis())) {
                            builder.field("analyzer", entry.getValue().getAnalysis());
                        }
                    }
                    builder.endObject();
                }
            }
            builder.endObject();
        }
        builder.endObject();

        return builder;
    }

    @Override
    public void deleteIndex(String indexName) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName);
        try {
            RestStatus status = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT).status();
            if (status != RestStatus.OK) {
                throw new ElasticSearchException(indexName + ":index delete fail");
            }
        } catch (IOException e) {
            throw new ElasticSearchException(e.getMessage());
        }
    }

    @Override
    public String getIndexName(Object object) {
        return object.getClass().getSimpleName();
    }
}
