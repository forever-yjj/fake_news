package com.zm.news.mapper;

import com.zm.news.entity.Tag;
import com.zm.news.entity.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.mapper
 * @className: TagMapper
 * @author: ZM
 * @description: 标签持久层接口
 * @date: 2021/1/17 11:24
 * @version: 1.0
 */
@Mapper
public interface TagMapper {

    /**
     * 标签列表 or 模糊查询
     * @param tag 搜索条件
     * @return java.util.List
     */
    List<Tag> findTagList(TagVo tag);

    /**
     * 根据主键查找
     * @param tagId 标签id
     * @return com.zm.news.entity.Tag
     */
    Tag findByPrimaryKey(int tagId);

    /**
     * 根据标签名称
     * @param tagName 标签名称
     * @return com.zm.news.entity.Tag
     */
    Tag findByTagName(String tagName);

    /**
     * 根据新闻id 查询 tagName
     * @param newsId 新闻id
     * @return java.util.List
     */
    List<Tag> findTagByNewsId(Integer newsId);

    /**
     * 中间表 tag_news
     * 根据新闻id查找
     * @param newsId 新闻id
     * @return java.util.List
     */
    List<Tag> findTagNewsByNewsId(Integer newsId);

    /**
     * 新增标签
     * @param tag 标签实体类
     * @return java.lang
     */
    int insertTag(Tag tag);

    /**
     * news_tag表添加
     * @param tagIds 标签Id
     * @param newsId 新闻id
     * @return java,lang
     */
    int insertNewsTag(Integer[] tagIds, Integer newsId);

    /**
     *根据主键修改
     * @param tag 标签实体类
     * @return java.lang
     */
    int updateByPrimaryKey(Tag tag);

    /**
     * 根据主键删除
     * @param tagId 标签id
     * @return java.lang
     */
    int deleteByPrimaryKey(int tagId);

    /**
     * 批量删除
     * @param tagIds java.util.List
     * @return java.lang
     */
    int deleteByPrimaryKeys(List<Tag> tagIds);

    /**
     * news_tag 根据新闻id批量删除
     * @param newsId 新闻id
     * @return java.lang
     */
    int deleteNewsTagByNewsId(int newsId);

    /**
     * 统计标签总数
     * @return java.lang
     */
    int getCount();

}
