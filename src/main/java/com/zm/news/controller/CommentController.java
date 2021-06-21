package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Comment;
import com.zm.news.entity.User;
import com.zm.news.entity.vo.CommentVo;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.controller
 * @className: CommentController
 * @author: ZM
 * @description: 评论控制层
 * @date: 2021/1/22 11:35
 * @version: 1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @GetMapping("/{id}")
    public String comment(@PathVariable Integer id, Model model) {
        List<Comment> comment = commentService.findCommentByNewsId(id);
        model.addAttribute("comments", comment);
        return "view/news :: commentList";
    }

    @PostMapping("/reply")
    public String reply(Comment comment) {
        User user = null;
        Object obj = session.getAttribute("user");
        if(!ObjectUtils.isEmpty(obj)) {
            user = (User) obj;
        }
        Integer newsId = comment.getNewsId();
        if(user != null) {
            comment.setUserId(user.getUserId());
            comment.setCommentDate(new Date());
            commentService.saveComment(comment);
        }
        return "redirect:/comment/" + newsId;
    }

    @GetMapping("/toCommentList")
    @ResponseBody
    public ResponseCode toCommentList(int page, int limit, CommentVo comment){
        return commentService.findCommentList(page, limit, comment);
    }

    @GetMapping("/removeComment")
    @ResponseBody
    public Boolean removeComment(Integer commentId) {
        return commentService.removeComment(commentId);
    }

    @GetMapping("/removeComments")
    @ResponseBody
    public Boolean removeComment(String commendIds) {
        return commentService.removeComments(commendIds);
    }
}
