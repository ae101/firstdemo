package com.nowcoder.firstdemo.dao;

import com.nowcoder.firstdemo.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by ae on 2018/8/15.
 */
@Mapper
@Repository
public interface UserDAO {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    //注解的方式映射程序和数据库。那么这里底层实现如何
    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS,") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id = #{id}"})
    User selectById(int id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where name = #{name}"})
    User selectByName(String name);

    @Update({"update",TABLE_NAME,"set password = #{password} where id = #{id}"})
    void updatePassword(User user);

    @Delete({"delete from", TABLE_NAME, "where id = #{id}"})
    void deleteById(int id);
}
