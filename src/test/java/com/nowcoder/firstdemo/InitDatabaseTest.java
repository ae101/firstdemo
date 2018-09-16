package com.nowcoder.firstdemo;

import com.nowcoder.firstdemo.dao.QuestionDAO;
import com.nowcoder.firstdemo.dao.UserDAO;
import com.nowcoder.firstdemo.model.Question;
import com.nowcoder.firstdemo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

/**
 * Created by ae on 2018/8/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FirstdemoApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTest {
    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void contextLoads() {
        Random random = new Random();
        for(int i = 0; i < 11; ++i) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");

            user.setPassword("psd");
            userDAO.updatePassword(user);
            userDAO.addUser(user);

            Question question = new Question();
            question.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000*3600*5*i);
            question.setCreatedDate(date);
            question.setTitle(String.format("TITLE{%d}", i));
            question.setContent(String.format("badlfasdjfkald Comment %d",i));
            question.setUserId(i + 1);
            questionDAO.addQuestion(question);
        }
        Assert.assertEquals("psd", userDAO.selectById(1).getPassword());
//        userDAO.deleteById(1);
//        Assert.assertNull(userDAO.selectById(1));

//        System.out.print(questionDAO.selectLatestQuestions(0,0,10));
    }
}
