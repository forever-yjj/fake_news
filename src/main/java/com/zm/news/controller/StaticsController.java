package com.zm.news.controller;

import com.zm.news.entity.Statistics;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName: news
 * @package: com.zm.news.controller
 * @className: StaticsController
 * @author: ZM
 * @description: 统计控制层
 * @date: 2021/3/11 10:01
 * @version: 1.0
 */
@Controller
@RequestMapping("/statics")
public class StaticsController extends BaseController {

    @RequestMapping("/toStaticsList")
    @ResponseBody
    public ResponseCode toStaticsList(int page, int limit, Statistics statistics, Integer type) {
        return statisticsService.findStaticsList(page,limit,statistics, type);
    }
}
