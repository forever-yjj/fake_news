package com.zm.news;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class NewsApplicationTests {

    @Autowired
    protected RestHighLevelClient client;

    @Test
    void contextLoads() throws IOException {

        SearchRequest request = new SearchRequest("fake-news");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("特朗普", "newsTitle", "newsDesc"))
                .from(1)
                .size(8)
                .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red;font-weight:500'>").postTags("</span>"));

        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

        System.out.println(response.getHits().getHits().length);
    }

}
