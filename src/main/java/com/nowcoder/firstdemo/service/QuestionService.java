package com.nowcoder.firstdemo.service;

import com.nowcoder.firstdemo.dao.QuestionDAO;
import com.nowcoder.firstdemo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ae on 2018/8/16.
 */

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset,limit);
    }

}
