package com.zm.news.entity.vo;

import com.zm.news.entity.Statistics;

import java.util.Date;

/**
 * @projectName: news
 * @package: com.zm.news.entity.vo
 * @className: StatisticsVo
 * @author: ZM
 * @description: 统计视图类
 * @date: 2021/2/2 12:26
 * @version: 1.0
 */
public class StatisticsVo extends Statistics {

    private String dateRange;

    private Date dateStart;

    private Date dateEnd;

    public StatisticsVo() {
    }

    public StatisticsVo(Integer id, String ip, Integer requestCount, Date requestDate, Integer location, String dateRange, Date dateStart, Date dateEnd) {
        super(id, ip, requestCount, requestDate, location);
        this.dateRange = dateRange;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
