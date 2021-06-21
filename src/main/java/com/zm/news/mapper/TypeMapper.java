package com.zm.news.mapper;

import com.zm.news.entity.Type;
import com.zm.news.entity.vo.TypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.mapper
 * @className: TypeMapper
 * @author: ZM
 * @description: 分类持久层接口
 * @date: 2021/1/17 10:35
 * @version: 1.0
 */
@Mapper
public interface TypeMapper {

    /**
     * 根据主键查找
     * @param typeId 分类id
     * @return com.zm.news.entity.Type
     */
    Type findByPrimaryKey(int typeId);

    /**
     * 查找分类列表 or 模糊查询
     * @param type 查询条件
     * @return java.util.List
     */
    List<Type> findTypeList(TypeVo type);

    /**
     * 新增分类
     * @param type 分类实体类
     * @return java.lang
     */
    int insertType(Type type);

    /**
     * 根据主键更新
     * @param type 分类实体类
     * @return java.lang
     */
    int updateByPrimaryKey(Type type);

    /**
     * 根据主键删除
     * @param typeId 分类id
     * @return java.lang
     */
    int deleteByPrimaryKey(int typeId);

    /**
     * 批量删除
     * @param types java.util.List
     * @return java.lang
     */
    int deleteByPrimaryKeys(List<Type> types);

    /**
     * 统计分类总数
     * @return java.lang
     */
    int getCount();
}
