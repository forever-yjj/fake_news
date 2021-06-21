package com.zm.news.rabbitmq;

import com.zm.news.entity.News;
import com.zm.news.redis.NewsKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @projectName: news
 * @package: com.zm.news.rabbitmq
 * @className: CodeConsumer
 * @author: ZM
 * @description: 验证码消费者
 * @date: 2021/2/10 10:45
 * @version: 1.0
 */
@Component
public class CodeConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "code"),
            key = {"code.send"}))
    public void saveNews(String message) {
        logger.warn("【RabbitMq】发送验证码开始，CODE："+message);
        //接口
        logger.warn("【RabbitMq】消费成功");
    }
}
