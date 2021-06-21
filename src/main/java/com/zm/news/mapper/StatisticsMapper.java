package com.zm.news.mapper;

import com.zm.news.entity.Statistics;
import com.zm.news.entity.vo.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.mapper
 * @className: StatisticsMapper
 * @author: ZM
 * @description: 统计持久层接口
 * @date: 2021/2/2 11:51
 * @version: 1.0
 */
@Mapper
public interface StatisticsMapper {
    /**
     * 查询所有统计信息
     * @return java.util.List
     */
    List<Statistics> selectAll();

    /**
     * 模糊查询
     * @param statistics 统计实体类
     * @param type 时间段
     * @return java.util.List
     */
    List<Statistics> findByParams(Statistics statistics, @Param("type") Integer type);

    /**
     * 获取从昨天开始近10天的日期
     * @return date
     */
    List<Date> getRecentDates();

    /**
     * 获取从昨天开始近10天每天的访问量
     * @return date
     */
    List<Integer> getRecentRequests();

    /**
     * 获取从昨天开始近10天每天的访客数
     * @return 访客数量
     */
    List<Integer> getRecentVisitors();

    /**
     * 根据主键查询
     * @param id 主键
     * @return 统计实体类
     */
    Statistics selectByPrimary(int id);

    /**
     * 根据ip查询
     * @param ip IP地址
     * @return 统计
     */
    Statistics selectByIp(String ip);

    /**
     * 获取不同时间段的访问数量
     * @param type 时间段
     * @return 访问数量
     */
    int getRequestCount(@Param("type") Integer type);

    /**
     * 获取不同时间段的访客数量
     * @param type 时间段
     * @return 访客数量
     */
    int getVisitorCount(@Param("type") Integer type);

    /**
     * 新增统计
     * @param statistics 统计实体类
     * @return 影响行数
     */
    int insert(Statistics statistics);

    /**
     * 根据主键更新
     * @param statistics 统计实体类
     * @return 影响行数
     */
    int updateByPrimary(Statistics statistics);

    /**
     * 根据ip更新
     * @param statistics 统计实体类
     * @return 影响行数
     */
    int updateByIp(Statistics statistics);

    /**
     * 访问次数自增
     * @param ip IP
     * @return 受影响的信息条数
     */
    int updateRequestCount(String ip);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响的行数
     */
    int deleteByPrimary(int id);

    /**
     * 获取总数
     * @return 信息数
     */
    int getCount();
}
