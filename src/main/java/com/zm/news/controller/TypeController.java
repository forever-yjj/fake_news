package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.News;
import com.zm.news.entity.Type;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.controller
 * @className: TypeController
 * @author: ZM
 * @description: 分类控制层
 * @date: 2021/1/20 10:57
 * @version: 1.0
 */
@Controller
@RequestMapping("/type")
public class TypeController extends BaseController{

    @RequestMapping("/{id}")
    public String toType(@PathVariable Integer id, Model model,@RequestParam(defaultValue = "1") Integer page) throws JsonProcessingException {

        List<Type> typeList = typeService.typeList();
        if(id == -1) {
            id = typeList.get(0).getTypeId();
        }
        model.addAttribute("types", typeList);
        PageInfo<News> pageInfo = newsService.findNewsByTypeId(id, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeId", id);
        model.addAttribute("page", page);
        return "view/types";
    }

    @PostMapping("/typeList")
    @ResponseBody
    public String typeList() {
        return typeService.findTypeList();
    }

    @GetMapping("/toTypeList")
    @ResponseBody
    public ResponseCode toTypeList(int page, int limit, String searchParams) throws JsonProcessingException {
        return typeService.findTypeList(page, limit, searchParams);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "admin/type/type-add";
    }

    @PostMapping("/addType")
    @ResponseBody
    public Boolean addType(String params) throws JsonProcessingException {
        return typeService.addType(params);
    }

    @GetMapping("/toEditType")
    public String toEditType(Integer typeId, Model model) {
        Type type = typeService.findTypeByTypeId(typeId);
        model.addAttribute("type", type);
        return "admin/type/type-edit";
    }

    @PostMapping("/editType")
    @ResponseBody
    public Boolean editType(String params) throws JsonProcessingException {
        return typeService.editType(params);
    }

    @GetMapping("/removeType")
    @ResponseBody
    public Boolean removeType(Integer typeId) {
        return typeService.removeType(typeId);
    }

    @GetMapping("/removeTypes")
    @ResponseBody
    public Boolean removeType(String typeIds) throws JsonProcessingException {
        return typeService.removeTypes(typeIds);
    }
}
