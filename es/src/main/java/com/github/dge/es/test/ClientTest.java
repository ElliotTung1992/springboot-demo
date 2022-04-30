package com.github.dge.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dge.es.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-21 22:05
 */
public class ClientTest {

    public static void main(String[] args) throws Exception{

        // 创建es客户端
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        ClientTest clientTest = new ClientTest();
        // clientTest.createIndex(restHighLevelClient);
        // clientTest.queryIndex(restHighLevelClient);
        // clientTest.delIndex(restHighLevelClient);
        // clientTest.createDoc(restHighLevelClient);
        // clientTest.updateDoc(restHighLevelClient);
        // clientTest.getDoc(restHighLevelClient);
        // clientTest.delDoc(restHighLevelClient);
        // clientTest.batch(restHighLevelClient);
        // clientTest.searchAll(restHighLevelClient);
        // clientTest.rangeSearch(restHighLevelClient);
        clientTest.fuzzySearch(restHighLevelClient);

        // 关闭es客户端
        restHighLevelClient.close();
    }

    /**
     * 组合查询
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 21:53
     */
    private void fuzzySearch(RestHighLevelClient restHighLevelClient) throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        /*FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("name", "蔡");
        searchSourceBuilder.query(fuzzyQuery);*/
        /*AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        searchSourceBuilder.aggregation(aggregationBuilder);*/
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupAge").field("age");
        searchSourceBuilder.aggregation(aggregationBuilder);
        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 组合查询
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 21:53
     */
    private void rangeSearch(RestHighLevelClient restHighLevelClient) throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");

        rangeQuery.gte(20);
        rangeQuery.lte(30);

        searchSourceBuilder.query(rangeQuery);
        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 组合查询
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 21:53
     */
    private void search(RestHighLevelClient restHighLevelClient) throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

//        boolQuery.must(QueryBuilders.matchQuery("sex", "男"));
        boolQuery.should(QueryBuilders.matchQuery("age", "27"));
        boolQuery.should(QueryBuilders.matchQuery("age", "28"));

        searchSourceBuilder.query(boolQuery);
        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 全匹配查询
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 21:41
     */
    private void searchAll(RestHighLevelClient restHighLevelClient) throws IOException {

        SearchRequest request = new SearchRequest();
        request.indices("user");
        request.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchAllQuery()));
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 批量插入
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 21:40
     */
    private void batch(RestHighLevelClient restHighLevelClient) throws IOException {
        BulkRequest request = new BulkRequest();

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user");
        indexRequest.id("1001");

        User user = new User();
        user.setName("张忠");
        user.setAge(32);
        user.setSex("男");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        indexRequest.source(userJson, XContentType.JSON);
        request.add(indexRequest);



        IndexRequest indexRequest2 = new IndexRequest();
        indexRequest2.index("user");
        indexRequest2.id("1002");

        User user2 = new User();
        user2.setName("肖彩娣");
        user2.setAge(27);
        user2.setSex("女");

        String userJson2 = mapper.writeValueAsString(user2);
        indexRequest2.source(userJson2, XContentType.JSON);
        request.add(indexRequest2);


        IndexRequest indexRequest3 = new IndexRequest();
        indexRequest3.index("user");
        indexRequest3.id("1003");

        User user3 = new User();
        user3.setName("蔡缙云");
        user3.setAge(28);
        user3.setSex("男");

        String userJson3 = mapper.writeValueAsString(user3);
        indexRequest3.source(userJson3, XContentType.JSON);
        request.add(indexRequest3);

        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
    }

    /**
     * 删除文档
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 20:56
     */
    private void delDoc(RestHighLevelClient restHighLevelClient) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index("user");
        deleteRequest.id("1001");
        DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    /**
     * 获取文档
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 20:50
     */
    private void getDoc(RestHighLevelClient restHighLevelClient) throws IOException {

        GetRequest getRequest = new GetRequest();
        getRequest.index("user");
        getRequest.id("1001");
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    /**
     * 更新文档
     * @param restHighLevelClient
     * @return void
     * @author dge
     * @date 2021-10-25 20:45
     */
    private void updateDoc(RestHighLevelClient restHighLevelClient) throws IOException {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user");
        updateRequest.id("1001");
        updateRequest.doc(XContentType.JSON, "sex", "女的");

        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    /**
     * 创建文档
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-25 20:34
     */
    private void createDoc(RestHighLevelClient restHighLevelClient) throws IOException {
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user");
        indexRequest.id("1001");

        User user = new User();
        user.setName("张忠");
        user.setAge(20);
        user.setSex("男的");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        indexRequest.source(userJson, XContentType.JSON);

        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    /**
     * 创建索引
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-21 22:17
     */
    private void createIndex(RestHighLevelClient restHighLevelClient) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("创建index:" + acknowledged);
    }

    /**
     * 查询索引
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-21 22:31
     */
    private void queryIndex(RestHighLevelClient restHighLevelClient) throws IOException {
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
    }

    /**
     * 删除索引
     * @param restHighLevelClient restHighLevelClient
     * @author dge
     * @date 2021-10-21 22:35
     */
    private void delIndex(RestHighLevelClient restHighLevelClient) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }
}
