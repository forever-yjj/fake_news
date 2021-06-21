package com.zm.news.rabbitmq;

import com.zm.news.elasticsearch.NewsRepository;
import com.zm.news.entity.News;
import com.zm.news.redis.NewsKey;
import com.zm.news.service.NewsService;
import com.zm.news.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @projectName: news
 * @package: com.zm.news.rabbitmq
 * @className: NewsConsumer
 * @author: ZM
 * @description: 新闻消费者
 * @date: 2021/2/10 10:37
 * @version: 1.0
 */
@Component
public class NewsConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private RedisUtils redis;

    @RabbitListener(bindings =
                @QueueBinding(
                        value = @Queue,
                        exchange = @Exchange(value = "es"),
                        key = {"news.save"}))
    public void saveNews(String message) {
        logger.warn("【RabbitMq】保存新闻的消息，ID："+message);
        News news = newsService.findNewsByNewsId(Integer.valueOf(message));
        newsRepository.save(news);
        redis.remove(NewsKey.getESCount.getPrefix());
        logger.warn("【RabbitMq】消费成功");
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "es"),
            key = {"news.delete"}))
    public void deleteNews(String message) {
        logger.warn("【RabbitMq】删除新闻的消息，ID："+message);
        newsRepository.deleteById(Integer.parseInt(message));
        redis.remove(NewsKey.getESCount.getPrefix());
        logger.warn("【RabbitMq】消费成功");
    }
}
