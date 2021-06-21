package com.zm.news.elasticsearch;

import com.zm.news.entity.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @projectName: news
 * @package: com.zm.news.elasticsearch
 * @className: NewsRepository
 * @author: ZM
 * @description: ElasticsearchRepository ：ES提供的操作CRUD接口
 *                    第一个参数：指定对象类型
 *                    第二个参数：ID类型
 * @date: 2021/2/2 15:48
 * @version: 1.0
 */
public interface NewsRepository extends ElasticsearchRepository<News, Integer> {
}
