package com.nowcoder.firstdemo.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.nowcoder.firstdemo.model.Question;

import java.util.List;

/**
 * Created by ae on 2018/8/15.
 */
@Mapper
@Repository

public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title, content, user_id, created_date, comment_count ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS,") values (#{title},#{content},#{userId},#{createdDate},#{commentCount})"})
    int addQuestion(Question question);

    @Select({"select ", SELECT_FIELDS," from ", TABLE_NAME, " where id=#{id}"})
    Question selectById(int id);

    @Update({"update ", TABLE_NAME," set comment_count = #{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id,@Param("commentCount") int commentCount);

    List<Question> selectLatestQuestions(@Param("userId") int userId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

}
