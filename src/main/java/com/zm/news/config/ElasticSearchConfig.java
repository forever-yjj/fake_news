package com.zm.news.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @projectName: news
 * @package: com.zm.news.config
 * @className: ElasticSearchConfig
 * @author: ZM
 * @description: ES配置类
 * @date: 2021/2/2 15:41
 * @version: 1.0
 */
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        // 定义客户端配置对象 9200端口
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("47.101.200.117:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
