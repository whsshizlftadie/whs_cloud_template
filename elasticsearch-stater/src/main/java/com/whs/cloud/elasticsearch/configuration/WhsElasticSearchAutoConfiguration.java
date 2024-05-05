package com.whs.cloud.elasticsearch.configuration;

import cn.hutool.core.util.StrUtil;
import com.whs.cloud.elasticsearch.usage.document.crud.opeartion.DocOperation;
import com.whs.cloud.elasticsearch.usage.document.search.operation.SearchOperation;
import com.whs.cloud.elasticsearch.usage.index.operation.IndexOperation;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableAutoConfiguration
@Configuration
@ConditionalOnClass(RestHighLevelClient.class)
public class WhsElasticSearchAutoConfiguration {

    @Value("${spring.elasticsearch.rest.uris}")
    private List<String> uris;
    @Value("${spring.elasticsearch.rest.password}")
    private String userName;
    @Value("${spring.elasticsearch.rest.username}")
    private String password;

    @Bean(value = "esHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        HttpHost[] httpHosts = createHosts();
        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts)
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        return restHighLevelClient;
    }

    @Bean
    public IndexOperation indexOperation() {
        return new IndexOperation(restHighLevelClient());
    }

    @Bean
    public DocOperation docOperation() {
        return new DocOperation(restHighLevelClient());
    }

    @Bean
    public SearchOperation searchOperation() {
        return new SearchOperation(restHighLevelClient());
    }

    private HttpHost[] createHosts() {
        HttpHost[] httpHosts = new HttpHost[uris.size()];
        for (int i = 0; i < uris.size(); i++) {
            String hostStr = uris.get(i);
            String[] host = hostStr.split(":");
            httpHosts[i] = new HttpHost(StrUtil.trim(host[0]), Integer.valueOf(StrUtil.trim(host[1])));
        }
        return httpHosts;
    }

    public List<String> getUris() {
        return uris;
    }

    public WhsElasticSearchAutoConfiguration setUris(List<String> uris) {
        this.uris = uris;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public WhsElasticSearchAutoConfiguration setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public WhsElasticSearchAutoConfiguration setPassword(String password) {
        this.password = password;
        return this;
    }
}
