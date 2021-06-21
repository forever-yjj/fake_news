package com.zm.news.entity.vo;

import com.zm.news.entity.Type;

import java.util.Date;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.entity.vo
 * @className: TypeVo
 * @author: ZM
 * @description: 分类视图
 * @date: 2021/1/20 11:27
 * @version: 1.0
 */
public class TypeVo extends Type {

    private String dateRange;

    private Date dateStart;

    private Date dateEnd;

    public TypeVo() {
    }

    public TypeVo(Integer typeId, String typeName, Date typeDate, String dateRange, Date dateStart, Date dateEnd) {
        super(typeId, typeName, typeDate);
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
