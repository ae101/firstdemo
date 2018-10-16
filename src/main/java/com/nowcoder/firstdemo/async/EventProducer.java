package com.nowcoder.firstdemo.async;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.firstdemo.util.JedisAdapter;
import com.nowcoder.firstdemo.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ae on 2018/10/15.
 */
@Service
public class EventProducer {
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel) {
        try {
            String json = JSONObject.toJSONString(eventModel);
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
