package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.News;
import com.zm.news.entity.Tag;
import com.zm.news.entity.Type;
import com.zm.news.redis.NewsKey;
import com.zm.news.service.BaseService;
import com.zm.news.service.NewsService;
import com.zm.news.utils.CacheUtils;
import com.zm.news.utils.ResponseCode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service.impl
 * @className: NewsServiceImpl
 * @author: ZM
 * @description: 业务层实现类
 * @date: 2021/1/21 10:09
 * @version: 1.0
 */
@Service
public class NewsServiceImpl extends BaseService implements NewsService {

    @Override
    public ResponseCode findNewsList(int page, int limit, News news){
        PageHelper.startPage(page, limit);
        List<News> newsList = newsMapper.findNewsList(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsList);
        return ResponseCode.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public News findNewsByNewsId(Integer newsId) {
        News news = newsMapper.findByPrimaryKey(newsId);
        news.setTypeList(typeMapper.findTypeList(null));
        return news;
    }

    @Override
    public News findNewsDetailById(Integer id) {
        News news = newsMapper.findByPrimaryKey(id);
        String key = NewsKey.getByViews.getPrefix() + ":" + id;
        int count = 0;
        if(redis.exists(key)) {
            count = Integer.parseInt((String) redis.get(key));
            news.setNewsViews(count);
        }
        int views = count == 0 ? news.getNewsViews(): count;
        //前端显示 浏览数量自增
        news.setNewsViews(views + 1);
        news.setTypeList(typeMapper.findTypeList(null));
        return news;
    }

    @Override
    public void increasedViews(Integer newsId) {
        String prefix = NewsKey.getByViews.getPrefix();
        String key = prefix + ":" + newsId;
        if(redis.exists(key)) {
            int views = Integer.parseInt((String) redis.get(key));
            redis.set(key, String.valueOf(views+1));
        }else {
            News news = newsMapper.findByPrimaryKey(newsId);
            redis.set(key, String.valueOf(news.getNewsViews() == null || news.getNewsViews() == 0 ? 0: news.getNewsViews()));
        }
    }

    @Override
    public PageInfo<News> findNewsByTypeId(Integer id, Integer page) {

        PageHelper.startPage(page, 8);
        List<News> list = newsMapper.findByTypeId(id);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<News> fuzzyQuery(int page, String query) throws IOException {

        List<News> result = new ArrayList<>();
        //创建搜索请求
        SearchRequest searchRequest = new SearchRequest("fake-news");
        //创建搜索对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置查询条件
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "newsTitle", "newsDesc", "newsContent"))
                           .from(page - 1)
                           .size((int) getEsDocCount())
                           .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red;font-weight:bolder'>").postTags("</span>"));
        searchRequest.source(searchSourceBuilder);

        //执行搜索

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit hit : searchHits) {
            Map<String, Object> map = hit.getSourceAsMap();
            News temp = new News();
            temp.setNewsId((int) map.get("newsId"));
            temp.setNewsViews((int) map.get("newsViews"));
            temp.setNewsTitle(map.get("newsTitle").toString());
            temp.setNewsCommentCount((int) map.get("newsCommentCount"));
            temp.setNewsDesc(map.get("newsDesc").toString());
            temp.setNewsImage(map.get("newsImage").toString());
            Type type = typeMapper.findByPrimaryKey((Integer) map.get("newsType"));
            temp.setType(type);

            Map<String, HighlightField> fields = hit.getHighlightFields();
            if(fields.containsKey("newsTitle")){
                temp.setNewsTitle(fields.get("newsTitle").fragments()[0].toString());
            }
            if(fields.containsKey("newsDesc")) {
                temp.setNewsDesc(fields.get("newsDesc").fragments()[0].toString());
            }
            result.add(temp);
        }
        return new PageInfo<>(result);
    }

    @Override
    public long getEsDocCount() throws IOException {
        // 创建搜索请求
        SearchRequest searchRequest = new SearchRequest("fake-news");
        // 创建搜索对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 设置查询条件
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchRequest.source(searchSourceBuilder);

        // 执行搜索
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        return response.getHits().getTotalHits().value;
    }

    @Override
    public void initEs() {
        newsRepository.deleteAll();
        List<News> newsList = newsMapper.findByStatus(1);
        for (News news : newsList) {
            newsRepository.save(news);
        }
    }

    @Override
    public PageInfo<News> orderByPublishTime(Integer page, int limit) throws JsonProcessingException {

        List<News> newsList = null;
        String realKey = NewsKey.getIndex.getPrefix();

        if(redis.exists(realKey)) {
            newsList = objectMapper.readValue((String) redis.get(realKey), new TypeReference<List<News>>() {});
        }else {
            PageHelper.startPage(page, limit);
            newsList = mergeNewsTag(newsMapper.orderByPublishTime());
            redis.set(realKey, objectMapper.writeValueAsString(newsList));
        }
        return new PageInfo<>(newsList);
    }

    @Override
    public List<News> queryByStatus(int i) {
        return newsMapper.findByStatus(i);
    }


    /**
     * 将标签存入news
     * @param list 新闻集合
     * @return list
     */
    private List<News> mergeNewsTag(List<News> list) {
        list.forEach(news -> {
            List<Tag> tagList = tagMapper.findTagByNewsId(news.getNewsId());
            news.setTagList(tagList);
        });

        return list;
    }

    @Override
    public List<News> orderByViews() {
        PageHelper.startPage(1, 10);
        List<News> newsList = newsMapper.orderByViews();
        return newsList;
    }

    @Override
    public List<News> orderByCommentCount() {
        PageHelper.startPage(1, 8);
        List<News> newsList = newsMapper.orderByCommentCount();
        return newsList;
    }

    @Override
    public Boolean addNews(String params) throws JsonProcessingException {
        News news = objectMapper.readValue(params, News.class);

        news.setNewsDate(new Date());
        news.setNewsStatus(0);
        newsMapper.insertNews(news);

        String tagIds = news.getTagIds();
        String[] name = tagIds.split(",");
        Integer[] ids = new Integer[name.length];
        for (int i = 0; i < name.length; i++) {
            ids[i] = tagMapper.findByTagName(name[i]).getTagId();
        }
        //添加
        tagMapper.insertNewsTag(ids, news.getNewsId());

        return true;
    }

    @Override
    public Boolean editNews(String params) throws JsonProcessingException {
        News news = objectMapper.readValue(params, News.class);
        String tagIds = news.getTagIds();
        String[] name = tagIds.split(",");
        Integer[] ids = new Integer[name.length];
        for (int i = 0; i < name.length; i++) {
            ids[i] = tagMapper.findByTagName(name[i]).getTagId();
        }
        //多表操作
        //删除
        tagMapper.deleteNewsTagByNewsId(news.getNewsId());
        //添加
        tagMapper.insertNewsTag(ids, news.getNewsId());
        news.setNewsDate(new Date());
        newsMapper.updateByPrimaryKey(news);
        return true;
    }

    @Override
    public Boolean editStatus(Integer newsId, Integer newsStatus) {
        News news = new News();
        news.setNewsId(newsId);
        news.setNewsStatus(newsStatus);
        return newsMapper.updateByPrimaryKey(news) == 1;
    }

    @Override
    public Boolean removeNews(Integer newsId) {
        tagMapper.deleteNewsTagByNewsId(newsId);
        return newsMapper.deleteByPrimaryKey(newsId) == 1;
    }

    @Override
    public Boolean removeNewsT(String newsIds) throws JsonProcessingException {
        List<News> newsList = objectMapper.readValue(newsIds, new TypeReference<List<News>>() {});
        for (News news : newsList) {
            tagMapper.deleteNewsTagByNewsId(news.getNewsId());
            CacheUtils.cleanNewsCache(redis, news.getNewsId());
            rabbitTemplate.convertAndSend("es", "news.delete", news.getNewsId());
        }
        newsMapper.deleteByPrimaryKeys(newsList);
        return true;
    }

    @Override
    public Integer totalCount() {
        return newsMapper.getCount();
    }
}
