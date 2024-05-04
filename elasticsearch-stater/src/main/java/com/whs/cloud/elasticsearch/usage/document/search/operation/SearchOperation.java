package com.whs.cloud.elasticsearch.usage.document.search.operation;

import com.whs.cloud.elasticsearch.ex.ElasticSearchException;
import com.whs.cloud.elasticsearch.usage.document.search.DefaultPageSearch;
import com.whs.cloud.elasticsearch.usage.document.search.DefaultQuerySearch;
import com.whs.cloud.elasticsearch.usage.document.search.page.ESPage;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

public class SearchOperation implements DefaultQuerySearch, DefaultPageSearch {

    private RestHighLevelClient restHighLevelClient;

    public SearchOperation(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public SearchResponse querySourceDefault(SearchRequest request, QueryBuilder queryBuilders) {

        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilders);
            request.source(sourceBuilder);
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            throw new ElasticSearchException(e.getMessage());
        }

    }


    @Override
    public ESPage pageSearch(SearchRequest searchRequest, QueryBuilder queryBuilder, int current, int size, Class clazz,
                             String orderField, SortOrder sortOrder) {

        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilder).from((current - 1) * size).size(size);

            if (!StringUtils.isEmpty(orderField) && ObjectUtils.isEmpty(sortOrder)) {
                sourceBuilder.sort(orderField);
            }
            if (!ObjectUtils.isEmpty(sortOrder) && !StringUtils.isEmpty(orderField)) {
                sourceBuilder.sort(orderField, sortOrder);
            }

            searchRequest.source(sourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            ESPage page = new ESPage<>();

            page.setCurrent(current).setSize(size).setTotal(response.getHits().getTotalHits());
            if (current == 1) {
                page.setFirstPage(true);
            } else {
                page.setFirstPage(false);
            }

            long endPage = page.getTotal() % size == 0 ? page.getTotal() / size : page.getTotal() / size + 1;

            if (endPage == current) {
                page.setEndPage(true);
            } else {
                page.setEndPage(false);
            }

            if (current > endPage) {
                throw new ElasticSearchException("您已经超过最大页数：" + endPage);
            }

            ConvertOperation operation = new ConvertOperation<>();
            List convertList = operation.convertList(response.getHits(), clazz);
            page.setContent(convertList);

            return page;
        } catch (IOException e) {
            throw new ElasticSearchException(e.getMessage());
        }

    }
}
