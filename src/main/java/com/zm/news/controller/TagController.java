package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Tag;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.controller
 * @className: TagController
 * @author: ZM
 * @description: 标签控制层
 * @date: 2021/1/20 16:12
 * @version: 1.0
 */
@Controller
@RequestMapping("/tag")
public class TagController extends BaseController{

    @GetMapping("/toTagList")
    @ResponseBody
    public ResponseCode toTagList(int page, int limit, String searchParams) throws JsonProcessingException {
        return tagService.findTagList(page, limit, searchParams);
    }

    @GetMapping ("/tagList")
    @ResponseBody
    public ResponseCode tagList(int page, int limit, String tagName) {
        return tagService.findTagListAll(page, limit, tagName);
    }
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "admin/tag/tag-add";
    }

    @PostMapping("/addTag")
    @ResponseBody
    public Boolean addTag(String params) throws JsonProcessingException {
        return tagService.addTag(params);
    }

    @GetMapping("/toEditTag")
    public String toEditTag(Integer tagId, Model model) {
        model.addAttribute("tag", tagService.findTagByTagId(tagId));
        return "admin/tag/tag-edit";
    }

    @PostMapping("/editTag")
    @ResponseBody
    public Boolean editTag(String params) throws JsonProcessingException {
        return tagService.editTag(params);
    }

    @GetMapping("removeTag")
    @ResponseBody
    public Boolean removeTag(Integer tagId) {
        return tagService.removeTag(tagId);
    }

    @GetMapping("/removeTags")
    @ResponseBody
    public Boolean removeTag(String tagIds) throws JsonProcessingException {
        return tagService.removeTags(tagIds);
    }
}
