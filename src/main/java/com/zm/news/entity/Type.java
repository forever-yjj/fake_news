package com.zm.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: Type
 * @author: ZM
 * @description: 分类实体类
 * @date: 2021/1/15 12:08
 * @version: 1.0
 */
public class Type implements Serializable {

    private static final long serialVersionUID = -22440854516264684L;

    private Integer typeId;

    private String typeName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date typeDate;

    public Type() {
    }

    public Type(Integer typeId, String typeName, Date typeDate) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDate = typeDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getTypeDate() {
        return typeDate;
    }

    public void setTypeDate(Date typeDate) {
        this.typeDate = typeDate;
    }


    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName=" + typeName +
                ", typeDate=" + typeDate +
                '}';
    }
}
