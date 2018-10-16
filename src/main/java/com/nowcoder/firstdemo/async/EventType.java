package com.nowcoder.firstdemo.async;

/**
 * Created by ae on 2018/10/15.
 */
public enum EventType {

    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3);

    private int value;
    EventType(int value) {this.value = value;}
    public int getEventType() {return value;}
}
