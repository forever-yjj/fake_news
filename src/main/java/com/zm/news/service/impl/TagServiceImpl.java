package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.Tag;
import com.zm.news.entity.vo.TagVo;
import com.zm.news.service.BaseService;
import com.zm.news.service.TagService;
import com.zm.news.utils.DateUtils;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service.impl
 * @className: TagServiceImpl
 * @author: ZM
 * @description: 标签业务层实现类
 * @date: 2021/1/20 16:17
 * @version: 1.0
 */
@Service
public class TagServiceImpl extends BaseService implements TagService {

    @Override
    public ResponseCode findTagList(int page, int limit, String searchParams) throws JsonProcessingException {

        PageHelper.startPage(page, limit);
        TagVo tag = new TagVo();
        if(searchParams != null){
            tag = objectMapper.readValue(searchParams, TagVo.class);
            //字符串转日期
            if(!"".equals(tag.getDateRange())) {
                String[] split = tag.getDateRange().split(" - ");
                Date dateStart = DateUtils.stringToDate(split[0]);
                Date dateEnd = DateUtils.stringToDate(split[1]);
                tag.setDateStart(dateStart);
                tag.setDateEnd(dateEnd);
            }
        }

        List<Tag> tagList = tagMapper.findTagList(tag);
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tagList);
        return ResponseCode.success(tagPageInfo.getList(), tagPageInfo.getTotal());
    }

    @Override
    public HashMap<String, Object> findTagByNewsId(Integer newsId) {
        List<Tag> tags = tagMapper.findTagNewsByNewsId(newsId);
        HashMap<String, Object> map = new HashMap<>();
        if(tags.size() != 0) {
            StringBuilder id = new StringBuilder();
            StringBuilder name = new StringBuilder();
            for (Tag tag : tags) {
                id.append(tag.getTagId()).append(",");
                name.append(tagMapper.findByPrimaryKey(tag.getTagId()).getTagName()).append(",");
            }
            String tagId = id.toString();
            String tagName = name.toString();
            System.out.println(tagId.length());
            System.out.println(tagName);

            map.put("tagId", tagId.substring(0, tagId.length() - 1));
            map.put("tagName", tagName.substring(0, tagName.length() - 1));
            return map;
        }else {
            map.put("tagId", "");
            map.put("tagName", "");
            return map;
        }
    }

    @Override
    public List<Tag> findByNewsId(Integer newsId) {
        return tagMapper.findTagByNewsId(newsId);
    }

    @Override
    public ResponseCode findTagListAll(int page, int limit, String tagName) {
        PageHelper.startPage(page, limit);
        TagVo tag = new TagVo();
        tag.setTagName(tagName);
        List<Tag> tagList = tagMapper.findTagList(tag);
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tagList);
        return ResponseCode.success(tagPageInfo.getList(), tagPageInfo.getTotal());
    }

    @Override
    public List<Tag> tagList() {
        return tagMapper.findTagList(null);
    }

    @Override
    public List<Tag> tagList(int limit) {

        //获取分类总数
        int count = tagMapper.getCount();
        //取总页数
        int range = (int)Math.ceil(count / limit + 0.5);
        //随机生成页数
        int page = random.nextInt(range - 1 + 1) + 1;

        PageHelper.startPage(page, limit);
        List<Tag> tagList = tagMapper.findTagList(null);
        return tagList;
    }



    @Override
    public Tag findTagByTagId(Integer tagId) {
        return tagMapper.findByPrimaryKey(tagId);
    }

    @Override
    public Boolean addTag(String params) throws JsonProcessingException {
        Tag tag = objectMapper.readValue(params, Tag.class);
        tag.setTagDate(new Date());
        tagMapper.insertTag(tag);
        return true;
    }

    @Override
    public Boolean editTag(String params) throws JsonProcessingException {
        Tag tag = objectMapper.readValue(params, Tag.class);
        tag.setTagDate(new Date());
        tagMapper.updateByPrimaryKey(tag);
        return true;
    }

    @Override
    public Boolean removeTag(Integer tagId) {
        return tagMapper.deleteByPrimaryKey(tagId) == 1;
    }

    @Override
    public Boolean removeTags(String tagIds) throws JsonProcessingException {
        List<Tag> tags = objectMapper.readValue(tagIds, new TypeReference<List<Tag>>() {});
        tagMapper.deleteByPrimaryKeys(tags);
        return true;
    }



}
