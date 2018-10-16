package com.nowcoder.firstdemo.async;

import java.util.List;

/**
 * Created by ae on 2018/10/15.
 */
public interface EventHandler {
    void  doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
