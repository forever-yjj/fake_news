package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.Type;
import com.zm.news.entity.vo.TypeVo;
import com.zm.news.redis.TypeKey;
import com.zm.news.service.BaseService;
import com.zm.news.service.TypeService;
import com.zm.news.utils.DateUtils;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service.impl
 * @className: TypeServiceImpl
 * @author: ZM
 * @description: 业务层实现类
 * @date: 2021/1/20 10:58
 * @version: 1.0
 */
@Service
public class TypeServiceImpl extends BaseService implements TypeService {

    @Override
    public ResponseCode findTypeList(int page, int limit, String searchParams) throws JsonProcessingException {

        //开启分页
        PageHelper.startPage(page, limit);
        TypeVo type = new TypeVo();
        if(searchParams != null) {
            type = objectMapper.readValue(searchParams, TypeVo.class);
            //字符串转日期
            if(!"".equals(type.getDateRange())){
                String[] split = type.getDateRange().split(" - ");
                Date dateStart = DateUtils.stringToDate(split[0]);
                Date dateEnd = DateUtils.stringToDate(split[1]);
                type.setDateStart(dateStart);
                type.setDateEnd(dateEnd);
            }
        }
        List<Type> typeList = typeMapper.findTypeList(type);
        PageInfo<Type> typePageInfo = new PageInfo<>(typeList);
        return ResponseCode.success(typePageInfo.getList(), typePageInfo.getTotal());
    }

    @Override
    public String findTypeList() {
        try {
            return objectMapper.writeValueAsString(typeMapper.findTypeList(null));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Type> typeList() throws JsonProcessingException {
        List<Type> list = null;
        String prefix = TypeKey.getType.getPrefix();
        if(redis.exists(prefix)) {
            list = objectMapper.readValue((String ) redis.get(prefix), new TypeReference<List<Type>>() {});
        }else {
            list = typeMapper.findTypeList(null);
            redis.set(prefix, objectMapper.writeValueAsString(list));
        }
        return list;
    }

    @Override
    public List<Type> typeList(int page, int limit) throws JsonProcessingException {
        String realKey = TypeKey.getIndex.getPrefix();
        String typeCount = TypeKey.getCount.getPrefix();
        int count = 0;
        //获取分类总数
        if(redis.exists(typeCount)) {
            count = Integer.parseInt((String) redis.get(typeCount));
        }else {
            count = typeMapper.getCount();
            redis.set(typeCount, String.valueOf(count));
        }

        //取总页数
        int range = (int)Math.ceil(count / limit + 0.5);
        //随机生成
        int num = random.nextInt(range - 1 + 1) + 1;
        List<Type> typeList = null;


        if(redis.exists(realKey)) {
            typeList = objectMapper.readValue((String) redis.get(realKey), new TypeReference<List<Type>>() {});
        }else {
            PageHelper.startPage(num, limit);
            typeList = typeMapper.findTypeList(null);
            redis.set(realKey, objectMapper.writeValueAsString(typeList));
        }
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        return pageInfo.getList();
    }


    @Override
    public Type findTypeByTypeId(Integer typeId) {
        return typeMapper.findByPrimaryKey(typeId);
    }

    @Override
    public Boolean addType(String params) throws JsonProcessingException {

        Type type = objectMapper.readValue(params, Type.class);
        type.setTypeDate(new Date());
        typeMapper.insertType(type);
        return true;
    }

    @Override
    public Boolean editType(String params) throws JsonProcessingException {
        Type type = objectMapper.readValue(params, Type.class);
        type.setTypeDate(new Date());
        typeMapper.updateByPrimaryKey(type);
        return true;
    }

    @Override
    public Boolean removeType(Integer typeId) {
        return typeMapper.deleteByPrimaryKey(typeId) == 1;
    }

    @Override
    public Boolean removeTypes(String typeIds) throws JsonProcessingException {
        List<Type> types = objectMapper.readValue(typeIds, new TypeReference<List<Type>>() {});
        typeMapper.deleteByPrimaryKeys(types);
        return true;
    }
}
