package com.nowcoder.firstdemo.controller;

import com.nowcoder.firstdemo.async.EventModel;
import com.nowcoder.firstdemo.async.EventProducer;
import com.nowcoder.firstdemo.async.EventType;
import com.nowcoder.firstdemo.model.Comment;
import com.nowcoder.firstdemo.model.EntityType;
import com.nowcoder.firstdemo.model.HostHolder;
import com.nowcoder.firstdemo.service.CommentService;
import com.nowcoder.firstdemo.service.LikeService;
import com.nowcoder.firstdemo.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ae on 2018/10/9.
 */
@Controller
public class LikeController {
    @Autowired
    HostHolder hostHolder;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return WendaUtil.getJSONString(999);
        }

        Comment comment = commentService.getCommentById(commentId);
        eventProducer.fireEvent(new EventModel(EventType.LIKE).setActorId(hostHolder.getUser().getId()).setEntityId(commentId)
        .setEntityType(EntityType.ENTITY_COMMENT).setEntityOwnerId(comment.getUserId())
        .setExt("questionId", String.valueOf(comment.getEntityId())));


        long likeCount = likeService.like(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
        return WendaUtil.getJSONString(0,String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return WendaUtil.getJSONString(999);
        }

        long likeCount = likeService.dislike(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
        return WendaUtil.getJSONString(0,String.valueOf(likeCount));
    }
}
