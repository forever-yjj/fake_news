package com.zm.news.entity.vo;

import com.zm.news.entity.News;
import com.zm.news.entity.Tag;

import java.util.Date;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.entity.vo
 * @className: TagVo
 * @author: ZM
 * @description: 标签视图
 * @date: 2021/1/20 16:08
 * @version: 1.0
 */
public class TagVo extends Tag {

    private String dateRange;

    private Date dateStart;

    private Date dateEnd;

    public TagVo() {}

    public TagVo(Integer tagId, String tagName, Date tagDate, String dateRange, Date dateStart, Date dateEnd) {
        super(tagId, tagName, tagDate);
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
